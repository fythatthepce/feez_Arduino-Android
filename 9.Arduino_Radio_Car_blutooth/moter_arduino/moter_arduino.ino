#include <SoftwareSerial.h>

//set pin numbers
//moter A : LEFT , RIGHT
#define ENA 5
#define IN1 6
#define IN2 7

//moter B : FORWARD , BACKWARD
#define ENB 10
#define IN3 9
#define IN4 8


SoftwareSerial BTSerial(11, 12); // RX | TX

char command; //command = string from android studio
String string;  //string of arduno


void setup() {
  //MOTER_A
  pinMode(ENA,OUTPUT);  //ENA
  pinMode(IN1,OUTPUT);  //IN1
  pinMode(IN2,OUTPUT);  //IN2

   //MOTER_B
  pinMode(ENB,OUTPUT);  //ENB
  pinMode(IN3,OUTPUT);  //IN3
  pinMode(IN4,OUTPUT);  //IN4

  Serial.begin(9600);
  BTSerial.begin(9600);  
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

//MOTER B
void forward(){
     //MOTER B
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,LOW);
     analogWrite(ENB,138);   //speed 138(5V)  MAX = 255(7.6V)
}
     

void backward(){
    //BACKWARD
      //MOTER B
     digitalWrite(IN3,LOW);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,138);   
}
//END MOTER B



//MOTER A
void left(){
     //MOTER A
     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,LOW);
     analogWrite(ENA,138);
}

void right(){
    //MOTER A
     digitalWrite(IN1,LOW);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,138);
}
//END MOTOR A

void stop_motor(){
      //MOTER A
     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,0);   

      //MOTER B
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,0); 
}
  
