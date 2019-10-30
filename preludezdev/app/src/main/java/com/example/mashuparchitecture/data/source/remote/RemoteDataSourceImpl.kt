package com.example.mashuparchitecture.data.source.remote

import com.example.mashuparchitecture.network.GithubApiService
import com.example.mashuparchitecture.network.vo.GithubRepositoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl(private val api: GithubApiService) :
    RemoteDataSource {

    override fun getGithubRepositories(
        query: String,
        onSuccess: (data: GithubRepositoriesResponse?) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        api
            .searchRepositories(query)
            .enqueue(object : Callback<GithubRepositoriesResponse?> {
                override fun onFailure(call: Call<GithubRepositoriesResponse?>, t: Throwable) {
                    onFail("데이터 요청에 실패했습니다.")
                }

                override fun onResponse(
                    call: Call<GithubRepositoriesResponse?>,
                    response: Response<GithubRepositoriesResponse?>
                ) {
                    val data = response.body()

                    if (data != null) {
                        when {
                            data.totalCount == 0 -> onFail("데이터가 없습니다.")
                            else -> onSuccess(data)
                        }
                    }
                }
            })
    }

}