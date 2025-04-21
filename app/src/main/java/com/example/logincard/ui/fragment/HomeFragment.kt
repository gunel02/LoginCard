package com.example.logincard.ui.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logincard.ui.adapter.AdapterAddItem
import com.example.logincard.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setFavouriteAdapter()

        return binding?.root
    }

    private fun setFavouriteAdapter() {
        binding?.addItemRv?.layoutManager = LinearLayoutManager(context)
        binding?.addItemRv?.adapter = AdapterAddItem()
    }

}