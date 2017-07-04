#include <arduino.h>

void setup()
{
     //Moter A
     pinMode(9,OUTPUT);   //ENA Analog 9
     pinMode(6,OUTPUT);   //IN1
     pinMode(7,OUTPUT);   //IN2


     //Moter B

}


void loop()
{
     //motor move Forward with speed
     digitalWrite(6,HIGH);
     digitalWrite(7,LOW);
     analogWrite(9,255);   //speed 255
     delay(3000);          //3 sec



     //motor move backward with speed
     digitalWrite(6,LOW);
     digitalWrite(7,HIGH);
     analogWrite(9,255);   //speed 255
     delay(3000);          //3 sec



     //stop motor with break
     digitalWrite(6,HIGH);
     digitalWrite(7,HIGH);
     analogWrite(9,0);     //speed 0
     delay(3000);          //3 sec



     //stop motor without break
     digitalWrite(6,LOW);
     digitalWrite(7,LOW);
     analogWrite(9,0);     //speed 0
     delay(3000);          //3 sec

}
