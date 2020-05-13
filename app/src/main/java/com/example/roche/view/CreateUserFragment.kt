package com.example.roche.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roche.R
import com.example.roche.model.CreateUserModelView
import com.example.roche.pojo.SensorData
import com.example.roche.pojo.User
import java.time.Year
import java.util.*


private val EXISTING_USER = "Existing USER"

class CreateUserFragment : Fragment(),View.OnClickListener,DatePickerDialog.OnDateSetListener {

    lateinit var mView:View
    lateinit var mUserid:EditText
    lateinit var mFirstName:EditText
    lateinit var mLastName:EditText
    lateinit var mDob:EditText
    lateinit var mEmail:EditText
    lateinit var mCellNumber:EditText
    lateinit var mAdrress:EditText
    lateinit var mHospitalId:EditText
    lateinit var mDoctorId:EditText
    lateinit var mDeviceId:EditText
    lateinit var mMalerb:RadioButton
    lateinit var mFemalerb:RadioButton
    lateinit var mPatientrb:RadioButton
    lateinit var mClinician:RadioButton
    lateinit var mSubmitbtn:Button

    lateinit var viewModel:ViewModel
     var mContext = getContext()
     var mYear: Int = 0
     var mDate:Int  = 0
     var mMonth:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(CreateUserModelView::class.java)
        mView = inflater.inflate(R.layout.layout_createusers, container, false);

        mUserid = mView.findViewById(R.id.useridedit)
        mFirstName = mView.findViewById(R.id.firstNameedit)
        mLastName = mView.findViewById(R.id.lastNameedit)
        mDob = mView.findViewById(R.id.dobedit)
        mEmail = mView.findViewById(R.id.emailedit)
        mCellNumber = mView.findViewById(R.id.cellnoedit)
        mAdrress = mView.findViewById(R.id.addressedit)
        mDoctorId = mView.findViewById(R.id.doctoridedit)
        mHospitalId = mView.findViewById(R.id.hospitalidedit)
        mDeviceId = mView.findViewById(R.id.deviceedit)
        mMalerb = mView.findViewById(R.id.malerbtn)
        mFemalerb = mView.findViewById(R.id.femalerbtn)
        mPatientrb = mView.findViewById(R.id.patientrbtn)
        mClinician = mView.findViewById(R.id.clinicianrbtn)
        mSubmitbtn = mView.findViewById(R.id.submitbutton)


        mSubmitbtn.setOnClickListener(this)
        mDob.isEnabled = false

        mDob.setOnClickListener(this)



        return mView
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.dobedit->{
                val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())
                val dialog = mContext?.let {
                    DatePickerDialog(
                        it, this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                }
                dialog?.show()
            }

            R.id.submitbutton->{
                if(validate()){
                    (viewModel as CreateUserModelView).
                    createUser(updateValue()).observe(this, Observer {
                        print(it)

                        activity?.
                        supportFragmentManager?.
                        beginTransaction()?.
                        replace(android.R.id.content,ExistingUserFragment())?.
                        addToBackStack(EXISTING_USER)?.
                        commit()
                    })

                }
            }
        }
    }

    private fun updateValue():User {
        val gender = if(mMalerb.isChecked) "M"  else {"F"}
        val userRole = if(mPatientrb.isChecked) "Patient"  else {"Clinician"}


        return User(mUserid.text.toString(),
            mFirstName.text.toString(),
            mLastName.text.toString(),
            gender,
            "$mDate/$mMonth/$mYear",
            mEmail.text.toString(),
            mCellNumber.text.toString(),
            userRole,
            mHospitalId.text.toString(),
            mDeviceId.text.toString(),
            mDoctorId.text.toString(),
            mutableListOf(SensorData(12,12.20,"12",20,1,1,1,1,1,1,1,1,1)),
            mAdrress.text.toString()
        )
    }

    private fun validate():Boolean {
        val nameRegex:Regex = Regex("[a-zA-Z.? ]*")
        val emailRegex:Regex = Regex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
        var validate:Boolean = false
        if(TextUtils.isEmpty(mUserid.text) || mUserid.length() < 0){
            mUserid.error ="Userid is wrong"
            validate = false
        }

        if(TextUtils.isEmpty(mFirstName.text) || mFirstName.text.matches(nameRegex)){
            mFirstName.error ="First Name is not proper"
            validate = false
        }

        if(TextUtils.isEmpty(mLastName.text) || mLastName.text.matches(nameRegex) ){
            mLastName.error ="Last name is not proper"
            validate = false
        }
        if(TextUtils.isEmpty(mEmail.text) || mEmail.text.matches(emailRegex)){
            mEmail.error ="Email is not proper"
            validate = false
        }
        if(TextUtils.isEmpty(mCellNumber.text) ||  mCellNumber.length() != 10){
            mCellNumber.error ="Mobile number is not correct"
            validate = false
        }
        if(TextUtils.isEmpty(mDoctorId.text) ){
            mDoctorId.error ="doctor id is not correct"
            validate = false
        }
        if(TextUtils.isEmpty(mHospitalId.text) ){
            mHospitalId.error ="Hospital id is not correct"
            validate = false
        }

        if(TextUtils.isEmpty(mDeviceId.text) ){
            mDeviceId.error ="Device id is not correct"
            validate = false
        }
        return validate
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
      mYear = year
        mMonth = month
        mDate = dayOfMonth
    }
}
