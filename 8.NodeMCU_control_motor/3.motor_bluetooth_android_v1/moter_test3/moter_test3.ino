#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

//set pin numbers
#define D0 16   //D0 = 16 

//pwm D1~D12
#define D1 5  
#define D2 4  
#define D3 0  
#define D4 2 
#define D5 14  
#define D6 12
#define D7 13
#define D8 15

//set BTSerial D4 = TX , D5 =  RX
SoftwareSerial BTSerial(D5, D4); // RX | TX

char command; //command = string from android studio
String string;  //string of arduno


void setup() {
  //MOTER_A
  pinMode(D1,OUTPUT);  //ENA
  pinMode(D2,OUTPUT);  //IN1
  pinMode(D3,OUTPUT);  //IN2

   //MOTER_B
  pinMode(D6,OUTPUT);  //ENB
  pinMode(D7,OUTPUT);  //IN3
  pinMode(D8,OUTPUT);  //IN4

  Serial.begin(9600);
  BTSerial.begin(9600);  
}




/*
void play(){
   char inByte;
   while (BTSerial.available() <= 0) 
   {
      //NULL
   }
   
   inByte = BTSerial.read();   //read char convert to value
   //char --> value
   //0 ---> inbyte = 48
   //1 ---> inbyte = 49
   //2 ---> inbyte = 50
   //3 ---> inbyte = 51

   //check value
   if(inByte==48)        //Stop moter = 0
      stop_motor();            
   else if(inByte==49)   //Forward  = 1
      forward();
   else if(inByte==50)   //Turn Left  = 2
      left();
   else if(inByte==51)   //Backward  = 3
      backward();
   else if(inByte==52)   //Turn Right = 4
      right();
}*/

void loop() {

     /*
     forward();
     delay(3000); //TIME FORWARD  = 3 SEC    
     
     left(); 
     delay(3000); //TIME TURNLEFT  = 3 SEC 
     */
     
     //play();

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
     //FORWARD
     //MOTER A
     digitalWrite(D2,HIGH);
     digitalWrite(D3,LOW);
     analogWrite(D1,255);   //speed 255
     
     
     //MOTER B
     digitalWrite(D7,HIGH);
     digitalWrite(D8,LOW);
     analogWrite(D6,255);   //speed 255
}

void backward(){
    //BACKWARD
      //MOTER A
     digitalWrite(D2,LOW);
     digitalWrite(D3,HIGH);
     analogWrite(D1,255);   //speed 255
     
     
     //MOTER B
     digitalWrite(D7,LOW);
     digitalWrite(D8,HIGH);
     analogWrite(D6,255);   //speed 255
}

void left(){
    //TURN LEFT
      //MOTER A
     digitalWrite(D2,HIGH);
     digitalWrite(D3,LOW);
     analogWrite(D1,255);   //speed 255

       //MOTER B
     digitalWrite(D7,HIGH);
     digitalWrite(D8,HIGH);
     analogWrite(D6,0);
}

void right(){

    //MOTER B
     digitalWrite(D7,HIGH);
     digitalWrite(D8,LOW);
     analogWrite(D6,255);  //speed 255
     
      //MOTER A
     digitalWrite(D2,HIGH);
     digitalWrite(D3,HIGH);
     analogWrite(D1,0);   
}

void stop_motor(){
      //MOTER A
     digitalWrite(D2,HIGH);
     digitalWrite(D3,HIGH);
     analogWrite(D1,0);   

      //MOTER B
     digitalWrite(D7,HIGH);
     digitalWrite(D8,HIGH);
     analogWrite(D6,0); 
}
  
