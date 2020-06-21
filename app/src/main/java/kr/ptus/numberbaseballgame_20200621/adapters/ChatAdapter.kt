package kr.ptus.numberbaseballgame_20200621.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kr.ptus.numberbaseballgame_20200621.R
import kr.ptus.numberbaseballgame_20200621.datas.Chat
import org.json.JSONObject
import org.w3c.dom.Text

class ChatAdapter(val mContext: Context, val resId: Int, val mList: List<Chat>) :
    ArrayAdapter<Chat>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        tempRow?.let {

        }.let {
            tempRow = inf.inflate(R.layout.chat_list_item, null)
        }

        val row = tempRow!!

        val computerLayout = row.findViewById<LinearLayout>(R.id.computerLayout)
        val computerTxt = row.findViewById<TextView>(R.id.computerTxt)
        val  myLayout = row.findViewById<LinearLayout>(R.id.myLayout)
        val myTxt = row.findViewById<TextView>(R.id.myTxt)

        val data = mList[position]

        if (data.who == "CPU"){
            computerLayout.visibility = View.VISIBLE
            myLayout.visibility = View.GONE


            computerTxt.text = data.content

        }
        else{
            computerLayout.visibility = View.GONE
            myLayout.visibility = View.VISIBLE

            myTxt.text = data.content

        }

        return row


    }

}