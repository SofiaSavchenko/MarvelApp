package com.example.marvelapp

import com.example.marvelapp.data.HeroData

open class HeroCard(

    open val imageResId: Int,
    open val nameResId: Int,

    )

data class HeroCardWithDesc(

    val descriptionResId: Int,
    override val imageResId: Int,
    override val nameResId: Int,

    ): HeroCard(imageResId, nameResId)


data class HeroCardWithBack(

    val backgroundResId: Int,
    override val imageResId: Int,
    override val nameResId: Int,

    ) : HeroCard(imageResId, nameResId)


private val numberOfHeroCards = HeroData.name.size

interface GetHeroCardsInterface {
    fun getHeroCards(): List<HeroCard>
}

object HeroCardsWithDesc: GetHeroCardsInterface {

    private val heroCardsList: List<HeroCardWithDesc> by lazy {
        (0 until numberOfHeroCards).map { i ->
            HeroCardWithDesc(

                descriptionResId = HeroData.description[i],
                imageResId = HeroData.image[i],
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
                imageResId = HeroData.image[i],
                nameResId = HeroData.name[i],
            )
        }
    }

    override fun getHeroCards(): List<HeroCardWithBack> {
        return heroCardsList
    }
}
