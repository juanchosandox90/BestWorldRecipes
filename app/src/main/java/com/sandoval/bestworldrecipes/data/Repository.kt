package com.sandoval.bestworldrecipes.data

import com.sandoval.bestworldrecipes.data.local.LocalDataSource
import com.sandoval.bestworldrecipes.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {

    val remoteDataSource = remoteDataSource
    val localDataSource = localDataSource

}