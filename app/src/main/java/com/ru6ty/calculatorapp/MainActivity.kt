package com.ru6ty.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var btnZero: Button? = null
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null
    private var btnDecimal: Button? = null
    private var btnDivide: Button? = null
    private var btnMultiply: Button? = null
    private var btnPlus: Button? = null
    private var btnMinus: Button? = null
    private var btnClear: Button? = null
    private var btnEqual: Button? = null
    var lastNumeric: Boolean = false
    var lastDecimal: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        btnZero = findViewById(R.id.btnZero)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnDecimal = findViewById(R.id.btnDot)
        btnDivide = findViewById(R.id.btnDivide)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnClear = findViewById(R.id.btnClear)
        btnEqual = findViewById(R.id.btnEqual)


        btnZero?.setOnClickListener {
            appendText(btnZero?.text)
        }

        btnOne?.setOnClickListener {
            appendText(btnOne?.text)
        }

        btnTwo?.setOnClickListener {
            appendText(btnTwo?.text)
        }

        btnThree?.setOnClickListener {
            appendText(btnThree?.text)
        }

        btnFour?.setOnClickListener {
            appendText(btnFour?.text)
        }

        btnFive?.setOnClickListener {
            appendText(btnFive?.text)
        }

        btnSix?.setOnClickListener {
            appendText(btnSix?.text)
        }

        btnSeven?.setOnClickListener {
            appendText(btnSeven?.text)
        }

        btnEight?.setOnClickListener {
            appendText(btnEight?.text)
        }

        btnNine?.setOnClickListener {
            appendText(btnNine?.text)
        }

        btnDecimal?.setOnClickListener {
            onDecimalPoint(btnDecimal?.text)
        }

        btnDivide?.setOnClickListener {
            onOperator(btnDivide?.text)
        }
        btnMultiply?.setOnClickListener {
            onOperator(btnMultiply?.text)
        }
        btnPlus?.setOnClickListener {
            onOperator(btnPlus?.text)
        }
        btnMinus?.setOnClickListener {
            onOperator(btnMinus?.text)
        }

        btnClear?.setOnClickListener {
            onClear()
        }

        btnEqual?.setOnClickListener {
            onEqual()
        }


    }

    private fun appendText(input: CharSequence?) {
        tvInput?.append(input)
        lastNumeric = true
        lastDecimal = false

    }

    private fun onOperator(input: CharSequence?) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it)) {
                tvInput?.append((input))
                lastNumeric = false
                lastDecimal = false
            }
        }
    }

    private fun onClear() {
        tvInput?.text = ""
    }

    private fun onDecimalPoint(input: CharSequence?) {
        if (lastNumeric && !lastDecimal) {
            tvInput?.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }

    //To enable negative values
    //Checks for other mathematical operators
    private fun isOperatorAdded(input: CharSequence): Boolean {
        return if (input.startsWith("-")) {
            false
        } else {
            input.contains("/")
                    || input.contains("*")
                    || input.contains("+")
                    || input.contains("-")
        }
    }

    private fun onEqual() {
        if (lastNumeric) {
            var tvValue = tvInput?.text
            var prefix = ""

            if (tvValue != null) {
                try {
                    if(tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }

                    if(tvValue.contains("-")){
                        val splitValue = tvValue.split("-")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tvInput?.text = removeDecimal((one.toDouble() - (two.toDouble())).toString())
                    }else if(tvValue.contains("+")) {
                        val splitValue = tvValue.split("+")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput?.text = removeDecimal((one.toDouble() + (two.toDouble())).toString())
                    }else if(tvValue.contains("*")) {
                        val splitValue = tvValue.split("*")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput?.text = removeDecimal((one.toDouble() * (two.toDouble())).toString())
                    }else if(tvValue.contains("/")) {
                        val splitValue = tvValue.split("/")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        tvInput?.text = removeDecimal((one.toDouble() / (two.toDouble())).toString())
                    }
                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun removeDecimal(result: String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }
}