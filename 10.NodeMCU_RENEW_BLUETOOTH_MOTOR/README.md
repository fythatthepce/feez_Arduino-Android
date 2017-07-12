# feez_Arduino-Android<br>
<br>
** การต่อ bluetooth = TX <-> RX , RX <-> TX โดยให้อัพโค้ดลง NodeMCU ก่อนต่อ TX , RX  และทุกครั้งที่จะทพการอัพโค้ดลง NodeMCU ให้ ถอด TX , RX ออกก่อน ลงโค้ดเสร็จแล้วค่อยต่อ **<br>
<B>How to control?</B><br>
<br>PWM หมายถึง Pulse Width Modulation เป็นเทคนิคที่ Arduino ใช้ในการควบคุมวงจร
และ เขียนค่าแบบอะนาล๊อก (Analog) ด้วยพอร์ตดิจิตัล (Digital) คือ โดยปกติแล้ว พอร์ตดิจิตัล จะสามารถมีได้แค่ 2 สถานะ คือ HIGH (5 โวล์ท) กับ LOW (0 โวล์ท)
เท่านั้น จึงทำให้สร้างค่าสัญญาณลอจิคได้เพียง เปิดหรือปิด (1 หรือ 0 , มีไฟหรือไม่มีไฟ) แค่นั้น
ซึ่งการใช้เทคนิค PWM นั้น จะเป็นการทำให้พอร์ตดิจิตัล สามารถเขียนค่าได้มากกว่า HIGH หรือ LOW
โดย ทำให้สามารถเขียนค่าเป็นแบบอะนาล๊อกได้ (อาจเป็น 0-255 หรือ 0-1023) <br>
ARDUINO-UNO-PWM = 3, 5, 6, 9, 10 และ 11 ตั้งค่าเอาท์พุทของ PWM ให้เป็น 8 bit ด้วยฟังก์ชั่น analogWrite()<br>
Moter ตัว H bridge ต้องเอา GND ที่บอร์ด arduino ต่อ H-bridge โดยใช้ powerbank เป็นแหล่งจ่ายหลัก ถ้าจะเพิ่มความเร็วก็เอาถ่านมาต่อเพิ่มตรง vcc และ GND ของ H-Bridge<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/map.png"/>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/control-motor.png"/><br>







