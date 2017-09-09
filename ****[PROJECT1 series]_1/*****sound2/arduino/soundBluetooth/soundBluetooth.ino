//HC-O5 Bluetooth
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


//set BTSerial D4 =  RX , D8 = TX 
SoftwareSerial BTSerial(D4, D8); // RX | TX

char command; //command = string from android studio
String string;  //string of arduno

//boolean ledon = false;


  void setup()
  {
    
    pinMode(D2, OUTPUT);
    Serial.begin(9600);
    BTSerial.begin(9600); 
  }

  void loop()
  {
    
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
    
    if(string == "RIGHT") //if string of arduino == TO
    {
        ledOn();
        //ledon = true;
    }
    
    if(string =="STOP") //if string of arduino == TF
    {
        ledOff();
         //ledon = false;
    }
    Serial.println(string);  //show string of arduino in serial monitor
    
 }


//fuct to do
void ledOn()
   {
      digitalWrite(D2,HIGH);
      delay(10);
    }
 
 void ledOff()
 {
      digitalWrite(D2, LOW);
      delay(10);
 }
 

  
