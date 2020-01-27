package com.droidknights.app2020.ui.info

import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseFragment
import com.droidknights.app2020.databinding.InfoFragmentBinding

/**
 * Created by jiyoung on 04/12/2019
 */
class InfoFragment :
    BaseFragment<InfoViewModel, InfoFragmentBinding>(
        R.layout.info_fragment,
        InfoViewModel::class
    )