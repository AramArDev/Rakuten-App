package com.example.rakuten_test_technique.presentation

/**
 * The sealed class [State] which gives different states of fragment ListProductFragment.
 */
sealed class State<out T> {
    object LoadingState : State<Nothing>()
    object ErrorState : State<Nothing>()
    object DataState : State<Nothing>()
}