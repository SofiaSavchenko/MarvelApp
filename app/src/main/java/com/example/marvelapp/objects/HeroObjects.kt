package com.example.marvelapp.objects

import androidx.compose.ui.graphics.Color
import com.example.marvelapp.data.HeroData

open class HeroCard(

    open val imageLink: String,
    open val nameResId: Int,

    )

data class HeroCardWithDesc(

    val descriptionResId: Int,
    override val imageLink: String,
    override val nameResId: Int,

    ) : HeroCard(imageLink, nameResId)


data class HeroCardWithBack(

    val backgroundColor: Color,
    override val imageLink: String,
    override val nameResId: Int,

    ) : HeroCard(imageLink, nameResId)


private val numberOfHeroCards = HeroData.name.size

object HeroCardsWithDesc {

    private val heroCardsList: List<HeroCardWithDesc> by lazy {
        (0 until numberOfHeroCards).map { i ->
            HeroCardWithDesc(

                descriptionResId = HeroData.description[i],
                imageLink = HeroData.image[i],
                nameResId = HeroData.name[i]

            )
        }
    }

    fun getHeroCards(): List<HeroCardWithDesc> {
        return heroCardsList
    }
}

object HeroCardsWithBack {

    private val numberOfHeroCards = HeroData.name.size

    private val heroCardsList: List<HeroCardWithBack> by lazy {
        (0 until numberOfHeroCards).map { i ->
            HeroCardWithBack(
                backgroundColor = HeroData.color[i],
                imageLink = HeroData.image[i],
                nameResId = HeroData.name[i],
            )
        }
    }

    fun getHeroCards(): List<HeroCardWithBack> {
        return heroCardsList
    }
}
