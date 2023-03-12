package com.allam.kotlinnews.network

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [LOCAL] represents the type for database.
 * [REMOTE] represents the type for network.
 */
@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<LOCAL, REMOTE> {
    private val TAG = "NetworkBoundRepository"

    fun asFlow() = flow<Resource<LOCAL>> {

        // Emit Database content first
        emit(Resource.Success(fetchFromLocal().first()))

        // Fetch latest posts from remote
        val apiResponse = fetchFromRemote()

        // Parse body
        val remotePosts = apiResponse.body()
        // Check for response validation
        if (apiResponse.isSuccessful && remotePosts != null) {
            // Save posts into the persistence storage
            saveRemoteData(remotePosts)
        } else {
            // Something went wrong! Emit Error state.
            emit(Resource.Failed(apiResponse.message()))
        }

        // Retrieve posts from persistence storage and emit
        emitAll(
            fetchFromLocal().map {
                Resource.Success<LOCAL>(it)
            }
        )
    }.catch {
        //log the error
        Log.e(TAG, "${it.message}")
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REMOTE)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<LOCAL>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REMOTE>
}