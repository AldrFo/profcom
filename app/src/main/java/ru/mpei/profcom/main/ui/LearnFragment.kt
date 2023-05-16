package ru.mpei.profcom.main.ui

import android.os.Bundle
import ru.mpei.profcom.core.BaseFragment
import ru.mpei.profcom.databinding.FragmentLearnBinding
import ru.mpei.profcom.main.model.LearningViewModel
import ru.mpei.profcom.main.model.entities.LearnDto

class LearnFragment(bundle: Bundle) : BaseFragment<FragmentLearnBinding, LearningViewModel>(
    LearningViewModel::class.java, FragmentLearnBinding::inflate
) {

    private var learn: LearnDto

    init {
        learn = bundle.getSerializable("learn") as LearnDto
    }

    override fun prepareViewModel() {}

    override fun bindViews() = with(binding) {
        title.text = learn.name
        description.text = learn.description
    }
}