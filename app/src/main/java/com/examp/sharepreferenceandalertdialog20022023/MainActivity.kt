package com.examp.sharepreferenceandalertdialog20022023

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var checkBoxRemember: CheckBox
    private lateinit var btnLogin: Button
    private lateinit var tvOutput: TextView
    private lateinit var imgDelete: ImageView
    private lateinit var viewGroupOutput: RelativeLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        displayOutput()
        eventView()
    }

    private fun eventView() {
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            val message = if (email.isEmpty()) {
                "Bạn chưa nhập email"
            } else if (password.isEmpty()) {
                "Bạn chưa nhập password"
            } else if (email == "abc@gmail.com" && password == "12345678"){
                saveAccount(email, password, checkBoxRemember.isChecked)
                "Đăng nhập thành công"
            } else {
                "Bạn đã nhập sai tài khoản"
            }

            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }

        imgDelete.setOnClickListener {
            showDialogDelete()
        }
    }

    private fun showDialogDelete() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Thông báo")
        alertDialogBuilder.setMessage("Bạn có muốn xóa dữ liệu?")

        alertDialogBuilder.setPositiveButton("Có") { dialog, positon ->

        }

        alertDialogBuilder.setNegativeButton("Không") { dialog, positon ->

        }

        alertDialogBuilder.show()
    }

    private fun initView() {
        edtEmail = findViewById(R.id.edit_text_email)
        edtPassword = findViewById(R.id.edit_text_password)
        checkBoxRemember = findViewById(R.id.check_box_save)
        btnLogin = findViewById(R.id.button_login)
        tvOutput = findViewById(R.id.text_view_output)
        imgDelete = findViewById(R.id.image_view_delete)
        viewGroupOutput = findViewById(R.id.relative_group_output)

        sharedPreferences = getSharedPreferences("App_cache", MODE_PRIVATE)
    }

    private fun saveAccount(
        email: String,
        password: String,
        isRemember: Boolean
    ) {
        if (isRemember) {
            sharedPreferences.edit {
                putString("email", email)
                putString("password", password)
                putBoolean("checked", true)
                apply()
            }
        } else {
            sharedPreferences.edit().clear().apply()
        }
    }

    private fun displayOutput() {
        sharedPreferences.apply {
            val email = getString("email", "")
            val password = getString("password", "")
            val isRemember = getBoolean("checked", false)

            if (isRemember) {
                viewGroupOutput.isVisible = true

                val spanned = SpannableStringBuilder().apply {
                    append("Email: $email")
                    append("\n")
                    append("Password: $password")
                }

                checkBoxRemember.isChecked = true
                tvOutput.text = spanned
                edtEmail.setText(email)
                edtPassword.setText(password)
            } else {
                viewGroupOutput.isVisible = false
                sharedPreferences.edit().clear().apply()
            }
        }
    }
}
