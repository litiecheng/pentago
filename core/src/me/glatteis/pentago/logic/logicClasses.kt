package me.glatteis.pentago.logic

import com.badlogic.gdx.graphics.Color
import me.glatteis.pentago.floorMod
import java.util.*

/**
 * Created by Linus on 21.07.2016!
 */

class Subtile(val width: Int) {
    var board = Array(width, {
        Array<Chip>(width, {
            NoChip
        })
    })

    var rotationInDegrees = 0

    fun rotate(direction: RotateDirection) {
        rotationInDegrees += if (direction == RotateDirection.CLOCKWISE) -90 else 90
        rotationInDegrees = floorMod(rotationInDegrees, 360)
    }

    fun rotated(): Array<Array<Chip>> {
        val newBoard = Array(width, {
            Array<Chip>(width, {
                NoChip
            })
        })
        for (i in board.indices) {
            for (j in board.indices) {
                when(rotationInDegrees) {
                    0 -> newBoard[i][j] = board[i][j]
                    90 -> newBoard[i][j] = board[j][board.size - 1 - i]
                    180 -> newBoard[i][j] = board[board.size - 1 - i][board.size - 1 - j]
                    270 -> newBoard[i][j] = board[board.size - 1 - j][i]
                }
            }
        }
        return newBoard
    }

}


enum class Mode {
    PUT, ROTATE
}

open class Chip(val player: Player?) {

}

object NoChip: Chip(null)

class Player(val color: Color, val name: String) {
    val uuid: UUID = UUID.randomUUID()
}

enum class RotateDirection {
    CLOCKWISE, COUNTERCLOCKWISE
}