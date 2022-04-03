package bmicalculator.project.priyanshu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)

        val weightSpinner = findViewById<Spinner>(R.id.weightSpinner)
        val heightSpinner = findViewById<Spinner>(R.id.heightSpinner)
        val calculate = findViewById<Button>(R.id.calculate)

        var unitWeight = "Kg"
        var unitHeight = "Cm"
        var weightValue : Double
        var heightValue : Double
        var bmiValue  : Double
        val bmiIndex = findViewById<TextView>(R.id.bmiIndex)
        val bmi = findViewById<TextView>(R.id.bmiText)
        val bmiCard = findViewById<CardView>(R.id.bmiCard)

        weightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterview: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when(position){
                    0 ->
                    {
                        unitWeight = "Kg"
                    }
                    1 ->
                    {
                        unitWeight = "lbs"
                    }

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        heightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterview: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 ->
                    {
                        unitHeight = "Cm"
                    }
                    1 ->
                    {
                        unitHeight = "ft"
                    }
                    2 ->
                    {
                        unitHeight = "Meter"
                    }
                    3 ->
                    {
                        unitHeight = "inch"
                    }

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        calculate.setOnClickListener {
                weight.onEditorAction(EditorInfo.IME_ACTION_DONE)
                height.onEditorAction(EditorInfo.IME_ACTION_DONE)
            try {
                weightValue=weight.text.toString().toDouble()
                heightValue=height.text.toString().toDouble()


                if(unitWeight=="lbs"){
                    weightValue *= 0.45359237
                }
                if (unitHeight=="ft"){
                    heightValue *= 0.3048
                }
                if(unitHeight=="Cm"){
                    heightValue *= 0.01
                }
                if(unitHeight=="inch"){
                    heightValue *= 0.0254
                }

                bmiValue = weightValue / (heightValue * heightValue)
                bmiValue="%.2f".format(bmiValue).toDouble()

                when {
                    50.0<=bmiValue -> {
                        Toast.makeText(this,"Your BMI doesn't look right. Please check the Entered data are Correct",Toast.LENGTH_SHORT).show()
                    }
                    40.0<=bmiValue -> {
                        bmiIndex.text=resources.getText(R.string.obesity3)
                        bmi.text = bmiValue.toString()
                        bmiCard.background.setTint(ContextCompat.getColor(this,R.color.obese3))
                    }
                    35.0<=bmiValue -> {
                        bmiIndex.text=resources.getText(R.string.obesity2)
                        bmi.text = bmiValue.toString()
                        bmiCard.background.setTint(ContextCompat.getColor(this,R.color.obese2))
                    }
                    30.0<=bmiValue -> {
                        bmiIndex.text=resources.getText(R.string.obesity1)
                        bmi.text = bmiValue.toString()
                        bmiCard.background.setTint(ContextCompat.getColor(this,R.color.obese1))
                    }
                    25.0<=bmiValue -> {
                        bmiIndex.text=resources.getText(R.string.overweight)
                        bmi.text = bmiValue.toString()
                        bmiCard.background.setTint(ContextCompat.getColor(this,R.color.overweight))
                    }
                    18.5<=bmiValue -> {
                        bmiIndex.text=resources.getText(R.string.healthy)
                        bmi.text = bmiValue.toString()
                        bmiCard.background.setTint(ContextCompat.getColor(this,R.color.healthy))
                    }
                    0.0 < bmiValue -> {
                        bmiIndex.text=resources.getText(R.string.underweight)
                        bmi.text = bmiValue.toString()
                        bmiCard.background.setTint(ContextCompat.getColor(this,R.color.underweight))
                    }
                    else -> {
                        Toast.makeText(this,"Your BMI doesn't look right. Please check the Entered data are Correct",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this,"Enter both fields",Toast.LENGTH_SHORT).show()
            }
        }
    }
}