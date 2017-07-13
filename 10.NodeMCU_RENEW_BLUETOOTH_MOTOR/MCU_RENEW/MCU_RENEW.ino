#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

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


//set BTSerial D4 =  RX , D8 = TX 
SoftwareSerial BTSerial(D4, D8); // RX | TX

char command; //command = string from android studio
String string;  //string of arduno


//1023 = 7 v
//650 = 5v

void setup() {
  //MOTER_A
  pinMode(ENA,OUTPUT);  //ENA
  pinMode(IN1,OUTPUT);  //IN1
  pinMode(IN2,OUTPUT);  //IN2

  //MOTER_B
  pinMode(ENB,OUTPUT);  //ENB
  pinMode(IN3,OUTPUT);  //IN3
  pinMode(IN4,OUTPUT);  //IN4
}



void loop() {
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
    
    Serial.println(string);  //show string of arduino in serial monitor
}

//function

void forward(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,LOW);
     analogWrite(ENB,650);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,LOW);
     analogWrite(ENA,650);   //speed 1023 //max cycle NodeMCU
}

void backward(){
     digitalWrite(IN3,LOW);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,650);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,LOW);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,650);   //speed 1023 //max cycle NodeMCU
}

void left(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,LOW);
     analogWrite(ENB,650);   //speed 1023 //max cycle NodeMCU

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
     analogWrite(ENA,650);   //speed 1023 //max cycle NodeMCU
}

void stop_motor(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,0);   //speed 1023 //max cycle NodeMCU

     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,0);   //speed 1023 //max cycle NodeMCU
}



  
