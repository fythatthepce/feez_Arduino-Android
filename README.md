# feez_Arduino-Android<br>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/arduino-uno3-pinout.jpg"/>

<br>
1.Dual H-bridge<br>
http://naringroup.blogspot.com/2016/03/robot-l298n-dual-h-bridge-motor.html<br>
http://naringroup.blogspot.com/2016/03/arduino-pwm.html<br>
https://www.arduinothai.com/article/13/arduino-%E0%B9%80%E0%B8%AD%E0%B8%B2%E0%B9%84%E0%B8%9B%E0%B8%97%E0%B8%B3%E0%B8%AD%E0%B8%B0%E0%B9%84%E0%B8%A3%E0%B9%84%E0%B8%94%E0%B9%89%E0%B8%9A%E0%B9%89%E0%B8%B2%E0%B8%87-%E0%B8%95%E0%B8%AD%E0%B8%99%E0%B8%97%E0%B8%B5%E0%B9%88-8-%E0%B8%81%E0%B8%B2%E0%B8%A3%E0%B9%83%E0%B8%8A%E0%B9%89%E0%B8%87%E0%B8%B2%E0%B8%99-l298n-motor-driver<br>

<br>

<B>How to control?</B><br>
<br>PWM หมายถึง Pulse Width Modulation เป็นเทคนิคที่ Arduino ใช้ในการควบคุมวงจร
และ เขียนค่าแบบอะนาล๊อก (Analog) ด้วยพอร์ตดิจิตัล (Digital) คือ โดยปกติแล้ว พอร์ตดิจิตัล จะสามารถมีได้แค่ 2 สถานะ คือ HIGH (5 โวล์ท) กับ LOW (0 โวล์ท)
เท่านั้น จึงทำให้สร้างค่าสัญญาณลอจิคได้เพียง เปิดหรือปิด (1 หรือ 0 , มีไฟหรือไม่มีไฟ) แค่นั้น
ซึ่งการใช้เทคนิค PWM นั้น จะเป็นการทำให้พอร์ตดิจิตัล สามารถเขียนค่าได้มากกว่า HIGH หรือ LOW
โดย ทำให้สามารถเขียนค่าเป็นแบบอะนาล๊อกได้ (อาจเป็น 0-255 หรือ 0-1023) <br>
ARDUINO-UNO-PWM = 3, 5, 6, 9, 10 และ 11 ตั้งค่าเอาท์พุทของ PWM ให้เป็น 8 bit ด้วยฟังก์ชั่น analogWrite()<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/control-motor.png"/><br>



2.NodeMCU + PlatformIO<br>
https://www.losant.com/blog/getting-started-with-platformio-esp8266-nodemcu<br>
<br>


