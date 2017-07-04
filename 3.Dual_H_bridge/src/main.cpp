#include <arduino.h>

void setup()
{
     //Moter A
     pinMode(10,OUTPUT);   //ENA Analog 9
     pinMode(2,OUTPUT);   //IN1
     pinMode(3,OUTPUT);   //IN2


/*
     //Moter B
     pinMode(10,OUTPUT);   //ENB Analog 10
     pinMode(2,OUTPUT);   //IN3
     pinMode(3,OUTPUT);   //IN4
     */

}


void loop()
{

     //MOTER A
     //motor move Forward with speed
     digitalWrite(2,HIGH);
     digitalWrite(3,LOW);
     analogWrite(10,255);   //speed 255
     delay(3000);          //3 sec


     /*
     //motor move backward with speed
     digitalWrite(6,LOW);
     digitalWrite(7,HIGH);
     analogWrite(9,255);   //speed 255
     delay(3000);          //3 sec

     //stop motor without break
     digitalWrite(6,LOW);
     digitalWrite(7,LOW);
     analogWrite(9,0);     //speed 0
     delay(3000);          //3 sec
     */



     //stop motor with break
     digitalWrite(2,HIGH);
     digitalWrite(3,HIGH);
     analogWrite(10,0);     //speed 0
     delay(3000);          //3 sec
  


//-----------------------------------------------------------------------------

/*
     //MOTER B
     //motor move Forward with speed
     digitalWrite(2,HIGH);
     digitalWrite(3,LOW);
     analogWrite(10,255);   //speed 255
     delay(3000);          //3 sec


     //stop motor with break
     digitalWrite(2,HIGH);
     digitalWrite(3,HIGH);
     analogWrite(10,0);     //speed 0
     delay(3000);          //3 sec
*/

}
