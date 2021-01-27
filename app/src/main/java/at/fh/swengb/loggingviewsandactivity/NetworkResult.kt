package at.fh.swengb.loggingviewsandactivity

sealed class NetworkResult<R> {
    data class Success<T>(val value: T): NetworkResult<T>()
    data class Error<Nothing>(val errorMessage: String): NetworkResult<Nothing>()
}