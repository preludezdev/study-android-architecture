package com.example.mashuparchitecture.data.source

import com.example.mashuparchitecture.data.source.local.LocalDataSourceImpl
import com.example.mashuparchitecture.data.source.remote.RemoteDataSourceImpl
import com.example.mashuparchitecture.network.vo.GithubRepositoriesResponse

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
) : Repository {
    override fun getGithubRepositories(
        query: String,
        onSuccess: (data: GithubRepositoriesResponse?) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        remoteDataSource.getGithubRepositories(query, onSuccess, onFail)
    }


}