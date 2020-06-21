package kr.ptus.numberbaseballgame_20200621

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.ptus.numberbaseballgame_20200621.adapters.ChatAdapter
import kr.ptus.numberbaseballgame_20200621.datas.Chat

class MainActivity : BaseActivity() {

    val chatList = ArrayList<Chat>()
    lateinit var mChatAdapter : ChatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()

    }
    override fun setupEvents() {



    }

    override fun setValues() {

        chatList.add(Chat("CPU","숫자야구 게임에 오신 것을 환영합니다."))
        chatList.add(Chat("CPU","세자리 숫자를 맞춰주세요."))
        chatList.add(Chat("CPU","0은 포함되지 않으며, 중복된 숫자도 없습니다."))

        mChatAdapter = ChatAdapter(mContext, R.layout.chat_list_item, chatList)
        ChatListView.adapter = mChatAdapter

    }
}
