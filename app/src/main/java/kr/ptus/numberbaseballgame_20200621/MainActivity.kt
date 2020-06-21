package kr.ptus.numberbaseballgame_20200621

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kr.ptus.numberbaseballgame_20200621.adapters.ChatAdapter
import kr.ptus.numberbaseballgame_20200621.datas.Chat
import org.w3c.dom.Document

class MainActivity : BaseActivity() {

    var inputTryCount = 0

    val cpuNumList = ArrayList<Int>()

    val chatList = ArrayList<Chat>()
    lateinit var mChatAdapter: ChatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        inputBtn.setOnClickListener {

            val inputNum = numberEdt.text.toString()

            if (inputNum.length != 3) {

                Toast.makeText(mContext, "세자리 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            chatList.add(Chat("ME", inputNum))

            mChatAdapter.notifyDataSetChanged()


            numberEdt.setText("")

            checkUserInputStrikeAndBall(inputNum)

        }


    }

    override fun setValues() {

        makeQuestionNum()

        for (num in cpuNumList) {
            Log.d("문제 출제", num.toString())
        }

        chatList.add(Chat("CPU", "숫자야구 게임에 오신 것을 환영합니다."))
        chatList.add(Chat("CPU", "세자리 숫자를 맞춰주세요."))
        chatList.add(Chat("CPU", "0은 포함되지 않으며, 중복된 숫자도 없습니다."))

        mChatAdapter = ChatAdapter(mContext, R.layout.chat_list_item, chatList)
        ChatListView.adapter = mChatAdapter


    }

    fun checkUserInputStrikeAndBall(input: String) {

        inputTryCount++

        val number = input.toInt()

        val numArr = ArrayList<Int>()

        numArr.add(number / 100)
        numArr.add(number / 10 % 10)
        numArr.add(number % 10)

        var strikeCount = 0
        var ballCount = -0

        for (i in numArr.indices) {

            for (j in cpuNumList.indices) {

                if (numArr[i] == cpuNumList[j]) {


                    if (i == j) {
                        strikeCount++
                    } else {
                        ballCount++
                    }
                }


            }

        }

        chatList.add(Chat("cpu", "${strikeCount}S ,${ballCount}B 입니다."))

        mChatAdapter.notifyDataSetChanged()

        if (strikeCount == 3){

            chatList.add(Chat("CPU","축하합니다."))
            chatList.add(Chat("CPU","${inputTryCount}회만에 맞췄습니다."))

            mChatAdapter.notifyDataSetChanged()

            runOnUiThread {
                Toast.makeText(mContext,"게임을 종료합니다.", Toast.LENGTH_SHORT).show()
            }

            numberEdt.isEnabled = false
            inputBtn.isEnabled = false

        }

    }

    fun makeQuestionNum() {

        for (i in 0..2) {

            while (true) {

                val randomNum = (Math.random() * 9 + 1).toInt()

                var duplCheckResult = true

                for (num in cpuNumList) {
                    if (num == randomNum) {
                        duplCheckResult = false
                    }
                }

                if (duplCheckResult) {

                    cpuNumList.add(randomNum)

                    break
                }
            }


        }

    }
}
