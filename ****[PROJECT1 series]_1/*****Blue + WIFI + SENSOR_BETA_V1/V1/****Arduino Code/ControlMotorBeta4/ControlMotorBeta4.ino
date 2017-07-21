#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

//WiFi
#include <WiFiClient.h> 
#include <ESP8266WebServer.h>
#include <EEPROM.h>
#include <WiFiUDP.h>

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

int speed_m = 750;

//enable/disable ultrasonic AND Laser
char ENA_SONIC = 0;
char ENA_LASER = 0;


//for ultra sonic
//TX RX
#define TX 1 
#define RX 3 

const int pingPin = TX;  //Trig
int inPin= RX;  //Echo
//END FOR Ultrasonic

//Laser
const int ldr  = A0;
int ldr_value  = 0;
//End Laser


//set BTSerial D4 =  RX , D8 = TX 
SoftwareSerial BTSerial(D4, D8); // RX | TX//set BTSerial D4 =  RX , D8 = TX 

/*
int led = D1;
int led2 = D3;
*/
char inbyte = 0;

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




//float voltageValue[4] = {0,1,2,3};
void setup() {
    /*
    pinMode(led, OUTPUT);
    pinMode(led2, OUTPUT);
    */

     //MOTER_A
    pinMode(ENA,OUTPUT);  //ENA
    pinMode(IN1,OUTPUT);  //IN1
    pinMode(IN2,OUTPUT);  //IN2

    //MOTER_B
    pinMode(ENB,OUTPUT);  //ENB
    pinMode(IN3,OUTPUT);  //IN3
    pinMode(IN4,OUTPUT);  //IN4

    //Serial.begin(9600);

    //wifi setup
    WiFi.softAP(WiFiName, WiFiPass);
    server.on("/", handleRoot);
    server.begin();
    udpConnected = connectUDP();
    if (udpConnected)
    {
        //NULL
    } 
}

//reset function
void(*resetFunc)(void) = 0;  //reset Function

void loop() {


   /*
  //Laser
   ldr_value = analogRead(ldr);
   
   if(ldr_value != 1024)
   {
     //digitalWrite(led,HIGH);
     forward();
   }else{
     stop_motor();  
    //digitalWrite(led,LOW);
   }
   */

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
   * 
   //for ultrasonic sonic
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

   if(cm == 3){
    sendAndroidValues2(); 
    
    
    //stop_led();
    stop_motor();
    
    //digitalWrite(led2, HIGH);
    backward();
    
    delay(1000);
    
    //stop_led();
    stop_motor();   
  }
  //End for ultrasonic
  */


  //can use ultrasonic functio
  
  if(ENA_SONIC == '1'){
     detect_obj();
  }else if(ENA_SONIC == '0'){
     detect_obj2();
  }

  if(ENA_LASER == '1'){
     laser_func();
  }else if(ENA_LASER == '0'){
     laser_func2();
  }
  
  
  
  //else{
  //  sendAndroidValues(); 
  //}
  

  //NEW Bluetooth Method
  if (BTSerial.available() > 0)  //if connect Bluetooth
  {

    /*not work
    //for ultrasonic in Bluetooth connect
    //Ultrasonic  Detect obj 3 cm function
    //detect_obj();
    //End Ultrasonic function
    */
    
  
    inbyte = BTSerial.read();
    if (inbyte == '0')
    {
      //LED off
      //digitalWrite(led, LOW);
      //ENA_SONIC = '1';
      stop_motor();
    }
    
    if (inbyte == '1')
    {
      //LED on
      //digitalWrite(led, HIGH);

      forward();
    }

     //backward
    if (inbyte == '2')  
    {
      //sendAndroidValues(); 
      //digitalWrite(led2, LOW);
      
      stop_motor();
    }  

     if (inbyte == '3')  
    {
      //sendAndroidValues2(); 
      //digitalWrite(led2, HIGH);
      
      backward();
    } 

    //left
    if (inbyte == '4')  
    {
       stop_motor();
    }  

     if (inbyte == '5')  
    {
       left();
    } 

    //right
    if (inbyte == '6')  
    {
        stop_motor();
    }  

     if (inbyte == '7')  
    {
        right();
    }  

     //Ultrasonic checkbox
    if (inbyte == '8')    //Check is false
    {
        //NULL
        ENA_SONIC = '0';
        //resetFunc();
    }  

     if (inbyte == '9')   //Check is true
    {
        ENA_SONIC = '1';
    }  

    
     if (inbyte == 'b')    //Check is false
    {
        //work state
        resetFunc();  
        

        //Test State
        //ENA_LASER = '0';
        
    }  

    if (inbyte == 'a')   //Check is true
    {
        ENA_LASER = '1';
    }
    

     if (inbyte == 'r')   //Disconnect Button
    {
        resetFunc();  
    }
    

    /*
    if (inbyte == '2')  //PUSH
    {
      sendAndroidValues();  //1
    }  

     if (inbyte == '3')  //RELEASE
    {
      sendAndroidValues2(); //0
    }*/
   
    
  }
  delay(50);
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

/*
void stop_led(){
     digitalWrite(led,LOW);
     digitalWrite(led2,LOW);
}*/

     
long microsecondsToCentimeters(long microseconds)
{
      // The speed of sound is 340 m/s or 29 microseconds per centimeter.
      // The ping travels out and back, so to find the distance of the
      // object we take half of the distance travelled.
      return microseconds / 29 / 2;
}

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

void detect_obj(){
   //Ultrasonic  Detect obj 3 cm function
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
   
    if(cm == 3){
      sendAndroidValues2(); 
      
      
      //stop_led();
      stop_motor();
      
      //digitalWrite(led2, HIGH);
      backward();
      
      delay(1000);
      
      //stop_led();
      stop_motor();   
    }
}


void detect_obj2(){
   //Ultrasonic  Detect obj 3 cm function
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
   
    if(cm == 3){
      //NULL
    }
}

void laser_func(){
   //Laser
   ldr_value = analogRead(ldr);
   
   if(ldr_value != 1024)
   {
     //digitalWrite(led,HIGH);
     
     forward();
   }else{
    //digitalWrite(led,LOW);
    
     stop_motor();  
   }
}

void laser_func2(){
   //Laser
   ldr_value = analogRead(ldr);
   if(ldr_value != 1024)
   {
    //NULL
   }else{
    //NULL
   }
}

