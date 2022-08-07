package bmicalculator.project.priyanshu.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import bmicalculator.project.priyanshu.R

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val obesityLimitConstant = 50.0
    private val obesity3LimitConstant = 40.0
    private val obesity2LimitConstant = 35.0
    private val obesity1LimitConstant = 30.0
    private val overWeightLimitConstant =25.0
    private val normalLimitConstant = 18.5
    private val underWeightLimitConstant = 0.0
    var bmiIndex = MutableLiveData<String>()
    var bmi = MutableLiveData<String>()
    var bmiCard = MutableLiveData<Int>()

    fun updateResult( bmiValue : Double){
        when {
            obesityLimitConstant<=bmiValue -> {
                Toast.makeText(getApplication<Application>().applicationContext,"Your BMI doesn't look right. Please check the Entered data are Correct",
                    Toast.LENGTH_SHORT).show()
            }
            obesity3LimitConstant<=bmiValue -> {
                bmiIndex.value=getApplication<Application>().applicationContext.getString(R.string.obesity3)
                bmi.value=bmiValue.toString()
                bmiCard.value=getColor(getApplication<Application>().applicationContext,R.color.obese3)
            }
            obesity2LimitConstant<=bmiValue -> {
                bmiIndex.value=getApplication<Application>().applicationContext.getString(R.string.obesity2)
                bmi.value=bmiValue.toString()
                 bmiCard.value=getColor(getApplication<Application>().applicationContext,R.color.obese2)
            }
            obesity1LimitConstant<=bmiValue -> {
                bmiIndex.value=getApplication<Application>().applicationContext.getString(R.string.obesity1)
                bmi.value=bmiValue.toString()
                 bmiCard.value=getColor(getApplication<Application>().applicationContext,R.color.obese1)
            }
            overWeightLimitConstant<=bmiValue -> {
                bmiIndex.value=getApplication<Application>().applicationContext.getString(R.string.overweight)
                bmi.value=bmiValue.toString()
                bmiCard.value=getColor(getApplication<Application>().applicationContext,R.color.overweight)
            }
            normalLimitConstant<=bmiValue -> {
                bmiIndex.value=getApplication<Application>().applicationContext.getString(R.string.healthy)
                bmi.value=bmiValue.toString()
                bmiCard.value=getColor(getApplication<Application>().applicationContext,R.color.healthy)
            }
            underWeightLimitConstant < bmiValue -> {
                bmiIndex.value=getApplication<Application>().applicationContext.getString(R.string.underweight)
                bmi.value=bmiValue.toString()
                 bmiCard.value=getColor(getApplication<Application>().applicationContext,R.color.underweight)
            }
            else -> {
                Toast.makeText(getApplication<Application>().applicationContext,"Your BMI doesn't look right. Please check the Entered data are Correct",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}