package com.example.marvelapp

import com.example.marvelapp.data.HeroData

open class HeroCard(

    open val imageLink: String,
    open val nameResId: Int,

    )

data class HeroCardWithDesc(

    val descriptionResId: Int,
    override val imageLink: String,
    override val nameResId: Int,

    ): HeroCard(imageLink, nameResId)


data class HeroCardWithBack(

    val backgroundResId: Int,
    override val imageLink: String,
    override val nameResId: Int,

    ) : HeroCard(imageLink, nameResId)


private val numberOfHeroCards = HeroData.name.size

interface GetHeroCardsInterface {
    fun getHeroCards(): List<HeroCard>
}

object HeroCardsWithDesc: GetHeroCardsInterface {

    private val heroCardsList: List<HeroCardWithDesc> by lazy {
        (0 until numberOfHeroCards).map { i ->
            HeroCardWithDesc(

                descriptionResId = HeroData.description[i],
                imageLink = HeroData.image[i],
                nameResId = HeroData.name[i]

            )
        }
    }

    override fun getHeroCards(): List<HeroCardWithDesc> {
        return heroCardsList
    }
}

object HeroCardsWithBack: GetHeroCardsInterface {

    private val numberOfHeroCards = HeroData.name.size

    private val heroCardsList: List<HeroCardWithBack> by lazy {
        (0 until numberOfHeroCards).map { i ->
            HeroCardWithBack(
                backgroundResId = HeroData.background[i],
                imageLink = HeroData.image[i],
                nameResId = HeroData.name[i],
            )
        }
    }

    override fun getHeroCards(): List<HeroCardWithBack> {
        return heroCardsList
    }
}
