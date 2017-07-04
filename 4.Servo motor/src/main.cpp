#include <arduino.h>
#include <Servo.h>
Servo myservo;
Servo myservo2;
void setup()
{
  myservo.attach(9);
  myservo2.attach(10);
}
void loop(){
       /*
        myservo.write(90);   //up -> turn right
        delay(1000);
        myservo.write(0);    //down -> turn left
        delay(5000);
        */

        //myservo.write(180);
        //delay(5000);

        /*
        myservo.write(270);
        delay(5000);
        myservo.write(360);
        delay(5000);
        */

        myservo2.write(360);   //up -> turn right
        delay(100000);
        //myservo2.write(270);    //down -> turn left
        //delay(5000);







}
