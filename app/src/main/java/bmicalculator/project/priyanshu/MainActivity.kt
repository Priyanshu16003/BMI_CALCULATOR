package bmicalculator.project.priyanshu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import bmicalculator.project.priyanshu.viewmodel.MainActivityViewModel
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private val lbsToKgConstant = 0.45359237
    private val ftToMeterConstant = 0.3048
    private val cmToMeterConstant = 0.01
    private val inchToMeterConstant = 0.0254
    private val meterToMeterConstant = 1.0
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)
        val weightSpinner = findViewById<Spinner>(R.id.weightSpinner)
        val heightSpinner = findViewById<Spinner>(R.id.heightSpinner)
        val calculate = findViewById<Button>(R.id.calculate)
        var weightValue : Double
        var heightValue : Double
        var bmiValue  : Double
        var heightConstant = 1.0
        var weightConstant = 1.0
        val bmiIndex = findViewById<TextView>(R.id.bmiIndex)
        val bmi = findViewById<TextView>(R.id.bmiText)
        val bmiCard = findViewById<CardView>(R.id.bmiCard)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        weightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterview: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when(position){
                    0 -> { weightConstant = 1.0}
                    1 -> { weightConstant = lbsToKgConstant }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }

        heightSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterview: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> { heightConstant = cmToMeterConstant }
                    1 -> { heightConstant = ftToMeterConstant }
                    2 -> { heightConstant = meterToMeterConstant}
                    3 -> { heightConstant = inchToMeterConstant }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) { }
        }

        viewModel.bmiIndex.observe(this) {
            bmiIndex.text = it.toString()
        }
        viewModel.bmi.observe(this) {
            bmi.text = it.toString()
        }
        viewModel.bmiCard.observe(this) {
            bmiCard.background.setTint(it)
        }

        calculate.setOnClickListener {
                weight.onEditorAction(EditorInfo.IME_ACTION_DONE)
                height.onEditorAction(EditorInfo.IME_ACTION_DONE)
            try {
                weightValue=weight.text.toString().toDouble()
                heightValue=height.text.toString().toDouble()

                weightValue *= weightConstant
                heightValue *= heightConstant

                bmiValue = weightValue / (heightValue * heightValue)
                bmiValue="%.2f".format(bmiValue).toDouble()
                viewModel.updateResult(bmiValue)
            }
            catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this,"Enter both fields",Toast.LENGTH_SHORT).show()
            }
        }
    }
}