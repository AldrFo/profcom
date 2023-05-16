package ru.mpei.profcom.main.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import ru.mpei.profcom.R
import ru.mpei.profcom.core.AdapterCallback
import ru.mpei.profcom.core.BaseFragment
import ru.mpei.profcom.core.RecyclerViewAdapter
import ru.mpei.profcom.databinding.FragmentGratitudesBinding
import ru.mpei.profcom.databinding.ItemGratitudeBinding
import ru.mpei.profcom.main.model.GratidudesViewModel
import ru.mpei.profcom.main.model.entities.GratitudeDto


class GratitudesFragment: BaseFragment<FragmentGratitudesBinding, GratidudesViewModel>(
    GratidudesViewModel::class.java, FragmentGratitudesBinding::inflate
) {

    private val adapter = object : RecyclerViewAdapter<GratitudeDto, ItemGratitudeBinding>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            ItemGratitudeBinding.inflate(layoutInflater, parent, false),
            object : AdapterCallback<GratitudeDto, ItemGratitudeBinding> {
                override fun bindViews(binding: ItemGratitudeBinding, item: GratitudeDto, position: Int): Unit = with(binding) {
                    name.text = item.name
                    semestr.text = getString(R.string.semestr, item.semestr)
                    dateOfAccept.text = getString(R.string.date_of_accept, item.dateOfAccept)
                    level.text = getString(R.string.level, item.level)
                    form.text = getString(R.string.form, item.form)
                    dates.text = getString(R.string.dates, item.dates)
                    numberOfDays.text = getString(R.string.number_of_days, item.numberOfDays)
                    orderButton.setOnClickListener {
                        val intent = Intent().apply {
                            action = Intent.ACTION_VIEW
                            addCategory(Intent.CATEGORY_BROWSABLE)
                            data = Uri.parse(item.orderLink)
                        }
                        startActivity(intent)
                    }
                }

                override fun onViewClicked(view: View?, item: GratitudeDto?) {}
            }
        )
    }

    override fun prepareViewModel() {
        viewModel.getGratitudes()
        viewModel.gratitudes.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun bindViews() {
        binding.gratitudesRecycler.adapter = adapter
    }
}