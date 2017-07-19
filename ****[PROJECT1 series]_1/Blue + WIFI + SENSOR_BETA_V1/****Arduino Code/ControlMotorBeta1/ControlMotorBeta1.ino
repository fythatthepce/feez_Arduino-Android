#include <ESP8266WiFi.h>
#include <WiFiClient.h> 
#include <ESP8266WebServer.h>
#include <EEPROM.h>
#include <WiFiUDP.h>

#include <SoftwareSerial.h>

//PIN SET NODEMCU V2
//pwm D1~D12
#define D1 5  
#define D2 4  
#define D3 0  
#define D4 2 
#define D5 14  
#define D6 12
#define D7 13
#define D8 15

//motor A
#define ENA D1
#define IN1 D2
#define IN2 D3

//motor B
#define ENB D5
#define IN3 D6
#define IN4 D7


//for ultra sonic
//TX RX
#define TX 1 
#define RX 3 

//test LED
int led =  D1;
int led2 =  D2;

const int pingPin = TX;  //Trig
int inPin= RX;  //Echo
//END FOR Ultrasonic


int speed_m = 750;

//INIT WIFI
const char WiFiName[] = "LED_via_NodeMCU_by_FeezCEkmitl";
const char WiFiPass[] = "123fee456";

char flag1;
String esid;

String s ="";
String string1 ="";

ESP8266WebServer server(8888);
unsigned int localPort = 8888;


WiFiUDP UDP;
boolean udpConnected = false;
char packetBuffer[UDP_TX_PACKET_MAX_SIZE]; //buffer to hold incoming packet,
 
char ReplyBuffer[] = "5"; // a string to send back

int serv=0;
int serv0=0;
int serv1=0;
int serv2=0;

boolean connectUDP();

void handleRoot() {
  server.send(200, "text/html", "<h1>You are connected</h1>");
}  
//END INIT WIFI


//INIT BLUETOOTH
//set BTSerial D4 =  RX , D8 = TX 
SoftwareSerial BTSerial(D4, D8); // RX | TX

char command; //command = string from android studio
String string;  //string of arduno

char inbyte = 0;


void setup() 
{
    Serial.begin(115200);
    EEPROM.begin(512);
    Serial.println();


     //MOTER_A
    pinMode(ENA,OUTPUT);  //ENA
    pinMode(IN1,OUTPUT);  //IN1
    pinMode(IN2,OUTPUT);  //IN2

    //MOTER_B
    pinMode(ENB,OUTPUT);  //ENB
    pinMode(IN3,OUTPUT);  //IN3
    pinMode(IN4,OUTPUT);  //IN4


    
    //TEST LED
    pinMode(led,OUTPUT);  //led
    pinMode(led2,OUTPUT);  //led

    WiFi.softAP(WiFiName, WiFiPass);
    server.on("/", handleRoot);
    server.begin();
    udpConnected = connectUDP();
    if (udpConnected)
    {
        //NULL
    } 
}

void(*resetFunc)(void) = 0;  //reset Function

void loop() 
{

  //WIFI CONTROL
  server.handleClient();
  if(udpConnected)
  {
        //if data available, read a packet
        int packetSize = UDP.parsePacket();
 
        if(packetSize)
        {
          Serial.println("");
          Serial.print("Received packet of size ");
          Serial.print("From ");
          IPAddress remote = UDP.remoteIP();
        for (int i =0; i < 4; i++)
        {
            Serial.print(remote[i], DEC);
            if (i < 3)
            { 
              Serial.print(".");
            }
        }
        Serial.print(", port ");
        Serial.println(UDP.remotePort());

        // read the packet into packetBufffer
        UDP.read(packetBuffer,UDP_TX_PACKET_MAX_SIZE);
        Serial.println("Contents:");



       if(packetSize>1 && packetSize<3)
        {
        
           serv0=packetBuffer[0];
           serv0= serv0-48;
           
           serv1=packetBuffer[1];
           serv1= serv1-48;
           
           serv=  serv1 +(serv0*10);
        
        }

         if(packetSize>2 && packetSize<4)
        {
        
            serv0=packetBuffer[0];
            serv0= serv0-48;
            
            serv1=packetBuffer[1];
            serv1= serv1-48;
            
            serv2=packetBuffer[2];
            serv2= serv2-48;
            
            serv=  serv2+(serv1*10) +(serv0*100);
        }

        if(packetSize>3 && packetSize<5)
        {
        
            serv0=packetBuffer[0];
            serv0= serv0-48;
            
            serv1=packetBuffer[1]; 
            serv1= serv1-48;
            
            serv2=packetBuffer[2];
            serv2= serv2-48;
            
            char serv3=packetBuffer[3];
            serv3= serv3-48;


            serv= serv3+ (serv2*10)+(serv1*100) +(serv0*1000);
        }

         if(packetSize>0 && packetSize<2)
        {
            serv=packetBuffer[0];
            serv=serv-48;
        }

    
        s =""; 
        for(int j=0;j<packetSize;j++)
        {
    
            s += (String)packetBuffer[j];
    
        }

        Serial.println("string");
        Serial.println(s);
        string1 =s;

        s ="";

        //UP
        if(serv==101)   //101 ON
        {
            forward();
            //UDP.println("101");
            serv=0;
         }
         
         if(serv==100)   //100 off
         {
            stop_motor();
            serv=0;
        }


        //DOWN
         if(serv==201)   
        {
            backward();
            serv=0;
         }
         
         if(serv==200)   
         {
            stop_motor();
            serv=0;
        }

         //LEFT
         if(serv==301)  
        {
            left();
            serv=0;
         }
         
         if(serv==300)
         {
            stop_motor();
            serv=0;
        }

        
         //RIGHT
         if(serv==401) 
        {
            right();
            serv=0;
         }
         
         if(serv==400) 
         {
            stop_motor();
            serv=0;
        }

        //reset
         if(serv==999) 
        {
            resetFunc();
            serv=0;
         }
        
    }
  }//END WIFI CONTROL



   /*
  //BLUETOOTH CONTROL
   if (BTSerial.available() > 0) 
    {string = "";}    //init string = NULL
   
    
    while(BTSerial.available() > 0)
    {
      command = ((byte)BTSerial.read());  //get string from android studio
      
      if(command == ':')
      {
        break;
      }
      
      else
      {
        string += command;  //move command(string from android studio) to string of arduino
      }
      
      delay(1);
    }
    
    if(string == "UP") //if string of arduino == UP
    {
        forward();
    }
    
    if(string =="DOWN") //if string of arduino == DOWN
    {
        backward();
    }

     if(string == "LEFT") //if string of arduino == LEFT
    {
        left();
    }
    
    if(string =="RIGHT") //if string of arduino == RIGHT
    {
        right();
    }
    
    if(string =="STOP") //if string of arduino == STOP
    {
        stop_motor();
    }

    if(string =="Disconnect") //if string of arduino == STOP
    {
        //stop_motor();
        resetFunc();
    }
    
    Serial.println(string);  //show string of arduino in serial monitor*/
  //END OLD Bluetooth


  //NEW Bluetooth Method
  long duration, cm;
     
   pinMode(pingPin, OUTPUT);
     

   digitalWrite(pingPin, LOW);
   delayMicroseconds(2);
   digitalWrite(pingPin, HIGH);
   delayMicroseconds(5);
   digitalWrite(pingPin, LOW);
   pinMode(inPin, INPUT);
   duration = pulseIn(inPin, HIGH);

   cm = microsecondsToCentimeters(duration);
      
  //for ultrasonic sonic
  //int cm = 14;
  if(cm == 15){
    sendAndroidValues();    //GREEN PIC Value = 1;
  }else{
    sendAndroidValues2();   //RED PIC Value = 0;
  }
  
  //sendAndroidValues();
  if (BTSerial.available() > 0)
  {
    inbyte = BTSerial.read();

    //forward
    if (inbyte == '0')  //RELEASE
    {
      //LED off
      digitalWrite(led, LOW);
      //stop_motor();
    }
    if (inbyte == '1')   ///PUSH
    {
      //LED on
       digitalWrite(led, HIGH);
       //forward();
    }

    //backward
    if (inbyte == '2')  
    {
      //sendAndroidValues();  
      //stop_motor();
      digitalWrite(led2, LOW);
    }  

     if (inbyte == '3')  
    {
      //sendAndroidValues2(); 
      //backward();
      digitalWrite(led2, HIGH);
    } 

    //left
    if (inbyte == '4')  
    {
       //stop_motor();
    }  

     if (inbyte == '5')  
    {
        //left();
    } 

    //right
    if (inbyte == '6')  
    {
        //stop_motor();
    }  

     if (inbyte == '7')  
    {
       //right();
    }  
    
  }
  //delay(50);
    
}//END LOOP

//WIFI FUNCTION
 boolean connectUDP()
 {
    boolean state = false;
    if(UDP.begin(localPort) == 1){
      state = true;
    }
    else{
      //NULL
    }
    return state;
}

//MOTER FUNCTION
void forward(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,LOW);
     analogWrite(ENB,speed_m);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,LOW);
     analogWrite(ENA,speed_m);   //speed 1023 //max cycle NodeMCU
}

void backward(){
     digitalWrite(IN3,LOW);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,speed_m);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,LOW);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,speed_m);   //speed 1023 //max cycle NodeMCU
}

void left(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,LOW);
     analogWrite(ENB,speed_m);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,0);   //speed 1023 //max cycle NodeMCU
}


void right(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,0);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,LOW);
     analogWrite(ENA,speed_m);   //speed 1023 //max cycle NodeMCU
}

void stop_motor(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,0);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,0);   //speed 1023 //max cycle NodeMCU
}

     
long microsecondsToCentimeters(long microseconds)
{
      // The speed of sound is 340 m/s or 29 microseconds per centimeter.
      // The ping travels out and back, so to find the distance of the
      // object we take half of the distance travelled.
      return microseconds / 29 / 2;
}


void sendAndroidValues()
 {
  //puts # before the values so our app knows what to do with the data
  BTSerial.print('#');
  //for loop cycles through 4 sensors and sends values via serial
  //for(int k=0; k<4; k++)
  //{
  BTSerial.print(1);
  BTSerial.print('+');
    //technically not needed but I prefer to break up data values
    //so they are easier to see when debugging
  //}
 BTSerial.print('~'); //used as an end of transmission character - used in app for string length
 BTSerial.println();
 delay(20);        //added a delay to eliminate missed transmissions
}

void sendAndroidValues2()
 {
  //puts # before the values so our app knows what to do with the data
  BTSerial.print('#');
  //for loop cycles through 4 sensors and sends values via serial
  //for(int k=0; k<4; k++)
  //{
  BTSerial.print(0);
  BTSerial.print('+');
    //technically not needed but I prefer to break up data values
    //so they are easier to see when debugging
  //}
 BTSerial.print('~'); //used as an end of transmission character - used in app for string length
 BTSerial.println();
 delay(20);        //added a delay to eliminate missed transmissions
}







