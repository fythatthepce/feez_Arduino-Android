#include <arduino.h>

void setup()
{
     pinMode(9,OUTPUT);   //ENA Analog 9
     pinMode(6,OUTPUT);   //IN1
     pinMode(7,OUTPUT);   //IN2
}


void loop()
{
     digitalWrite(6,HIGH);      //ตามตารางข้างบน ต้องกำหนด IN1 = HIGH
     digitalWrite(7,LOW);       //และ IN2 = LOW มอเตอร์ A จึงจะทำงานหมุนไปด้านหน้า
     analogWrite(9,255);       //และสั่งหมุนที่ความเร็วสูงสุด 255 ผ่านทาง ENA (ขา 10) ที่เป็น PWM

     delay(3000);                     //รอเวลา 3 วินาที


     digitalWrite(6,HIGH);       //ตามตารางข้างบน ต้องกำหนด IN1 = HIGH
     digitalWrite(7,HIGH);       //และ IN2 = HIGH มอเตอร์ A จึงจะหยุดการทำงานทันที (เบรค)
     analogWrite(9,0);            //และสั่งความเร็วมอเตอร์ให้เป็น 0 ผ่านทาง ENA (ขา 10) ที่เป็น PWM
     delay(3000);

}
