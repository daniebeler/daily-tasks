package com.daniebeler.dailytasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TodayFragment : Fragment(), TodoAdapter.OnItemClickListener, TodoAdapter.OnItemLongclickListener {

    private var dbHandler: DBHandler? = null
    private var rv_dashboard: RecyclerView? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_today, container, false)

        dbHandler = DBHandler(requireActivity().applicationContext)

        rv_dashboard = view.findViewById(R.id.rv_dashboard_today)
        rv_dashboard?.layoutManager = LinearLayoutManager(activity?.applicationContext)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refreshList()
    }

    fun refreshList(){
        rv_dashboard?.adapter = TodoAdapter(dbHandler!!.getToDos("today"), this, this)
    }

    override fun onItemClick(position: Int) {
        dbHandler!!.updateToDo(position, "today")
        refreshList()
    }

    override fun onItemLongClick(position: Int) {
        dbHandler!!.deleteToDo(position, "today")
        refreshList()
    }
}