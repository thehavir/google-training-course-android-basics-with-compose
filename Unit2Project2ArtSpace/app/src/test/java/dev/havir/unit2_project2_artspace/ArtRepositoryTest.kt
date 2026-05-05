package dev.havir.unit2_project2_artspace

import dev.havir.unit2_project2_artspace.data.ArtRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtRepositoryTest {
    @Test
    fun artReturnsVaseOfFlowersArtWhenCurrentArtIndexIs0() {
        val art = ArtRepository.arts[0]

        assertEquals(art.imageResourceId, R.drawable.vase_of_flowers)
        assertEquals(
            art.titleResourceId, R.string.vase_of_flowers_title
        )
        assertEquals(art.subtitleResourceId, R.string.vase_of_flowers_subtitle)
    }

    @Test
    fun artReturnsJeremiahArtWhenCurrentArtIndexIs1() {
        val art = ArtRepository.arts[1]

        assertEquals(art.imageResourceId, R.drawable.jeremiah)
        assertEquals(art.titleResourceId, R.string.jeremiah_title)
        assertEquals(art.subtitleResourceId, R.string.jeremiah_subtitle)
    }

    @Test
    fun artReturnsDrivingHomeArtWhenCurrentArtIndexIs2() {
        val art = ArtRepository.arts[2]

        assertEquals(art.imageResourceId, R.drawable.driving_home)
        assertEquals(art.titleResourceId, R.string.driving_home_title)
        assertEquals(art.subtitleResourceId, R.string.driving_home_subtitle)
    }

    @Test
    fun artReturnsPearsArtWhenCurrentArtIndexIs3() {
        val art = ArtRepository.arts[3]

        assertEquals(art.imageResourceId, R.drawable.pears)
        assertEquals(art.titleResourceId, R.string.pears_title)
        assertEquals(
            art.subtitleResourceId, R.string.pears_subtitle
        )
    }

    @Test
    fun artReturnsWomanWithAParasolArtWhenCurrentArtIndexIs4() {
        val art = ArtRepository.arts[4]

        assertEquals(art.imageResourceId, R.drawable.woman_with_a_parasol)
        assertEquals(art.titleResourceId, R.string.woman_with_a_parasol_title)
        assertEquals(
            art.subtitleResourceId, R.string.woman_with_a_parasol_subtitle
        )
    }

    @Test
    fun artReturnsArtichokeFlowersArtWhenCurrentArtIndexIs5() {
        val art = ArtRepository.arts[5]

        assertEquals(art.imageResourceId, R.drawable.artichoke_flowers)
        assertEquals(art.titleResourceId, R.string.artichoke_flowers_title)
        assertEquals(
            art.subtitleResourceId, R.string.artichoke_flowers_subtitle
        )
    }

    @Test
    fun artReturnsCourtingArtWhenCurrentArtIndexIs6() {
        val art = ArtRepository.arts[6]

        assertEquals(art.imageResourceId, R.drawable.courting)
        assertEquals(art.titleResourceId, R.string.courting_title)
        assertEquals(art.subtitleResourceId, R.string.courting_subtitle)
    }
}