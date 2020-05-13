package com.example.roche.pojo

data class User(var userId:String,
                var firstName:String,
                var lastName:String,
                var gender:String,
                var dob:String,
                var email:String,
                var cellNumber:String,
                var userRole:String,
                var hospitalId:String,
                var deviceId:String,
                var doctorId:String,
                var sensorData:MutableList<SensorData>,
                var address:String
)


data class SensorData(var bloodOxygenation:Int,
                      var skinTemp:Double,
                      var skinBloodPerfusion:String,
                      var respirationRate:Int,
                      var hrv:Int,
                      var ibi:Int,
                      var steps:Int,
                      var stress:Int,
                      var sleep:Int,
                      var bpw:Int,
                      var energyExpenditure:Int,
                      var sweat:Int,
                      var healthscores:Int
)


data class UserData(var statusMsg:String,
                    var data: User
)

data class UserList(var statusMsg:String,
                    var data: MutableList<User>)

data class Status(var code:Int,
                  var message:String
)