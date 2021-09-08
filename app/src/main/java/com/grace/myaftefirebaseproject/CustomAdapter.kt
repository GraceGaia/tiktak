package com.grace.myaftefirebaseproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class CustomAdapter(var context: Context, var data:ArrayList<User>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtName:TextView
        var mTxtEmail:TextView
        var mTxtIdNumber:TextView
        var buttonDelete:Button
        var buttonUpdate:Button

        init {
            this.mTxtName = row?.findViewById(R.id.mName) as TextView
            this.mTxtEmail = row?.findViewById(R.id.mItemEmail) as TextView
            this.mTxtIdNumber = row?.findViewById(R.id.mItemAddress) as TextView
            this.buttonDelete = row?.findViewById(R.id.mBtnDelete) as Button
            this.buttonUpdate = row?.findViewById(R.id.mBtnUpdate) as Button
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.item_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:User = getItem(position) as User
        viewHolder.mTxtName.text = item.name
        viewHolder.mTxtEmail.text = item.email
        viewHolder.mTxtIdNumber.text = item.id_number
        viewHolder.buttonDelete.setOnClickListener {
            var reference = FirebaseDatabase.getInstance().getReference().child("Users/${item.id}")
            reference.removeValue()
            Toast.makeText(context,"User deleted successfully",Toast.LENGTH_LONG).show()
        }

        //How to push data saved top the update section
        viewHolder.buttonUpdate.setOnClickListener {
            var updateIntent = Intent(context,UpdateActivity::class.java)
            updateIntent.putExtra("w",item.name)
            updateIntent.putExtra("x",item.email)
            updateIntent.putExtra("y",item.id_number)
            updateIntent.putExtra("z",item.id)
            context.startActivity(updateIntent)
        }

        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}