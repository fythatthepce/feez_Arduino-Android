//HC-O5 Bluetooth
#include <SoftwareSerial.h>
SoftwareSerial BTSerial(10, 11); // RX | TX

char command; //command = string from android studio
String string;  //string of arduno
boolean ledon = false;
#define led 13

  void setup()
  {
    
    pinMode(led, OUTPUT);
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
    
    if(string == "TO") //if string of arduino == TO
    {
        ledOn();
        ledon = true;
    }
    
    if(string =="TF") //if string of arduino == TF
    {
        ledOff();
        ledon = false;
    }
    Serial.println(string);  //show string of arduino in serial monitor
    
 }


//fuct to do
void ledOn()
   {
      analogWrite(led, 255);
      delay(10);
    }
 
 void ledOff()
 {
      analogWrite(led, 0);
      delay(10);
 }
 

    
