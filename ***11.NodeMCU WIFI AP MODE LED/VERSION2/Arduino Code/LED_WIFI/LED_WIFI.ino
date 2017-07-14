#include <ESP8266WiFi.h>
#include <WiFiClient.h> 
#include <ESP8266WebServer.h>
#include <EEPROM.h>
#include <WiFiUDP.h>

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



void setup() 
{
    Serial.begin(115200);
    EEPROM.begin(512);
    Serial.println();

    
    pinMode(D1,OUTPUT);
    pinMode(D2,OUTPUT);
    pinMode(D3,OUTPUT);
    pinMode(D4,OUTPUT);
     
    digitalWrite(D1,LOW);
    digitalWrite(D2,LOW);
    digitalWrite(D3,LOW);
    digitalWrite(D4,LOW);

    WiFi.softAP(WiFiName, WiFiPass);
    server.on("/", handleRoot);
    server.begin();
    udpConnected = connectUDP();
    if (udpConnected)
    {
        //NULL
    } 
}


void loop() 
{
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

        //LED1
        if(serv==101)   //101 LED1 on
        {
            digitalWrite(D1,HIGH);
            //UDP.println("101");
            serv=0;
         }
         
         if(serv==100)   //100 LED1 off
         {
            digitalWrite(D1,LOW);
            serv=0;
        }


        //LED2
         if(serv==201)   //1 LED1 on
        {
            digitalWrite(D2,HIGH);
            serv=0;
         }
         
         if(serv==200)   //o LED1 off
         {
            digitalWrite(D2,LOW);
            serv=0;
        }

         //LED3
         if(serv==301)   //1 LED1 on
        {
            digitalWrite(D3,HIGH);
            serv=0;
         }
         
         if(serv==300)   //o LED1 off
         {
            digitalWrite(D3,LOW);
            serv=0;
        }

        
         //LED4
         if(serv==401)   //1 LED1 on
        {
            digitalWrite(D4,HIGH);
            serv=0;
         }
         
         if(serv==400)   //o LED1 off
         {
            digitalWrite(D4,LOW);
            serv=0;
        }
        
    }
  }
}


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






