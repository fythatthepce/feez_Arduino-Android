# Bluetooth<br>
<br>
ref:http://blog.redlinesoft.net/?p=3003<br>
connect : <br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/b1.png"/>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/b2.png"/>
<br>
<br>
Setting : <br>
ลองพิมพ์ AT จากนั้นกด Enter จะขึ้นว่า OK หรือ ERROR แสดงว่าต่อวงจรถูกแล้ว และโมดูลอยู่ในโหมดพร้อมรับคำสั่งตั้งค่าแล้ว<br>
คำสั่งเปลี่ยนชื่ออุปกรณ์ คือคำสั่ง AT+NAME="Device Name" เช่น หากต้องการเปลี่ยนชื่อเป็น IOXhop HC5 ต้องพิมพ์ว่า AT+NAME="IOXhop HC5" แล้วกด Enter หากสำเร็จจะขึ้น OK<br>
เปลี่ยนพาสที่ใช้จับคู่ ใช้คำสั่ง AT+PSWD=New Password เช่น ต้องการตั้งค่าเป็นรหัส 9876 ต้องใช้คำสั่ง AT+PSWD=9876<br>
<br><br>
Connect Mobile : <br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/b3.png"/>
<br><br>
จากนั้นไปโหลดแอพ Bluetooth Terminal มา แอพนี้จะใช้สำหรับส่งข้อมูลไปให้โมดูล HC05 สามารถโหลดได้ที่ : https://play.google.com/store/apps/details?id=Qwerty.BluetoothTerminal&hl=th<br>

 เปิดแอพขึ้นมา จะพบกับหน้าต่างง่ายๆ หากชื่อในช่องตรงกับชื่อของโมดูล HC05 ที่ได้ตั้งไว้ ก็สามารถกด Connect ได้เลย
<br>






