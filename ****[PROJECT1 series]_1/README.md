# feez_Arduino-Android<br>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/nodemcu_pins.png"/>
<br>
<br>//PIN SET NODEMCU V2
<br>//pwm D1~D12
<br>#define D1 5  
<br>#define D2 4  
<br>#define D3 0  
<br>#define D4 2 
<br>#define D5 14  
<br>#define D6 12
<br>#define D7 13
<br>#define D8 15
<br>
<br>
<br>
Dual H-bridge<br>
http://naringroup.blogspot.com/2016/03/robot-l298n-dual-h-bridge-motor.html<br>
http://naringroup.blogspot.com/2016/03/arduino-pwm.html<br>
https://www.arduinothai.com/article/13/arduino-%E0%B9%80%E0%B8%AD%E0%B8%B2%E0%B9%84%E0%B8%9B%E0%B8%97%E0%B8%B3%E0%B8%AD%E0%B8%B0%E0%B9%84%E0%B8%A3%E0%B9%84%E0%B8%94%E0%B9%89%E0%B8%9A%E0%B9%89%E0%B8%B2%E0%B8%87-%E0%B8%95%E0%B8%AD%E0%B8%99%E0%B8%97%E0%B8%B5%E0%B9%88-8-%E0%B8%81%E0%B8%B2%E0%B8%A3%E0%B9%83%E0%B8%8A%E0%B9%89%E0%B8%87%E0%B8%B2%E0%B8%99-l298n-motor-driver<br>

<br>
** การต่อ bluetooth = TX <-> RX , RX <-> TX โดยให้อัพโค้ดลง NodeMCU ก่อนต่อ TX , RX  และทุกครั้งที่จะทพการอัพโค้ดลง NodeMCU ให้ ถอด TX , RX ออกก่อน ลงโค้ดเสร็จแล้วค่อยต่อ **<br>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/blue1.png"/>
<br>

<B>How to control?</B><br>
Moter ตัว H bridge ต้องเอา GND ที่ NodeMCU ต่อ H-bridge โดยใช้ powerbank หรือเอาถ่านชาร์จต่อ เข้าขา vin(5 ถึง 10v)เป็นแหล่งจ่ายไฟให้ NodeMCU ถ้าจะเพิ่มความเร็วก็เอาถ่านมาต่อเพิ่มตรง vcc และ GND ของ H-Bridge<br>
<br>NodeMCU pwm 0 - 1023
<br>Arduino pwm 0 - 255
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/map.png"/>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/control-motor.png"/><br>

<br>
<br>

<B>Application Control via WIFI and BLUETOOTH</B>
<br>
BLUETOOTH MODE
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/****%5BPROJECT1%20series%5D_1/pic/4.png"/>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/****%5BPROJECT1%20series%5D_1/pic/5.png" />
<br>ref:http://www.instructables.com/id/Android-Aplication-for-Robot-Control-Using-HC-05-a/<br>
<br>ref:http://www.instructables.com/id/Android-Bluetooth-Control-LED-Part-2/<br>

<br>
<br>
<br>
<br>

WIFI MODE
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/****%5BPROJECT1%20series%5D_1/pic/6.png" />
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/****%5BPROJECT1%20series%5D_1/pic/7.png" />
<br>ref:https://www.swymhome.com/projects/106-esp-and-android-app?tab=project-files<br>

<br>Fragment :
<br>ref : https://www.youtube.com/watch?v=2gMqbDfDzu8
<br>ref : https://stackoverflow.com/questions/20835933/intent-from-fragment-to-activity





