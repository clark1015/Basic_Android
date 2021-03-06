package fastcampus.aop.part2.chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }
    private val addButton: Button by lazy {
        findViewById<Button>(R.id.addButton)
    }

    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val previousNumTextView: List<TextView> by lazy {
        listOf<TextView>(
                    findViewById<TextView>(R.id.first_generated_num),
                    findViewById<TextView>(R.id.second_generated_num),
                    findViewById<TextView>(R.id.third_generated_num),
                    findViewById<TextView>(R.id.fourth_generated_num),
                    findViewById<TextView>(R.id.fifth_generated_num),
                    findViewById<TextView>(R.id.sixth_generated_num)
        )
    }

    private var didRun = false

    private val pickedByUserNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }
    private fun initRunButton(){
        runButton.setOnClickListener{
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed{
                index, number -> val textView = previousNumTextView[index]

                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }
            Log.d("MainActivity", list.toString())
        }
    }

    private fun initAddButton() {
        addButton.setOnClickListener{

            if (didRun) {
                Toast.makeText(this, "????????? ?????? ??????????????????. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickedByUserNumberSet.size >= 5){
                Toast.makeText(this, "????????? 5???????????? ????????? ??? ????????????. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickedByUserNumberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "?????? ????????? ???????????????. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = previousNumTextView[pickedByUserNumberSet.size]
            Log.d("MainActivity", previousNumTextView[pickedByUserNumberSet.size].text.toString())
            textView.isVisible = true
            textView.text = numberPicker.value.toString()

            setNumberBackground(numberPicker.value, textView)



            pickedByUserNumberSet.add(numberPicker.value)



        }
    }

    private fun setNumberBackground(number: Int, textView: TextView) {
        when(number) {
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }

    private fun initClearButton() {
        clearButton.setOnClickListener{
            pickedByUserNumberSet.clear()
            previousNumTextView.forEach{
                it.isVisible= false
            }

            didRun = false
        }
    }

    private fun getRandomNumber(): List<Int>{
        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45){
                    if (pickedByUserNumberSet.contains(i)){
                        continue
                    }

                    this.add(i)
                }
            }
        numberList.shuffle()

        val newList = pickedByUserNumberSet.toList() + numberList.subList(0,6 - pickedByUserNumberSet.size)

        return newList.sorted()
    }
}