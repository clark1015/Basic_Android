package fastcampus.aop.part2.aop_part2_chapter01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        //R.layout.activity_main 화면을 가져와 contentview로 설정하겠다는 의미
        //R=Resource R.주소를 이용해서 더 간편하게 주소값 대신

        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText = findViewById<EditText>(R.id.weightEditText)

        val resultButton = findViewById<Button>(R.id.resultButton)

        resultButton.setOnClickListener{
            Log.d("MainActivity","ResultButton이 클릭되었습니다")

            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()){
                Toast.makeText(this,"빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //이 아래로는 절대 빈 값이 올 수 없음

            val height: Int = heightEditText.text.toString().toInt()
            val weight: Int = weightEditText.text.toString().toInt()

            val intent = Intent(this, resultActivity::class.java)
            startActivity(intent)

            Log.d("MainActivity", "height: ${height} weight: ${weight}")



        } //listener:듣는 것 클릭이 일어나는 것을 듣는 것

    }
}