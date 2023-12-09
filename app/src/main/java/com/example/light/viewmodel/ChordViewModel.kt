package com.example.light.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.light.R
//import com.example.light.app.ChordUseCase
//import com.example.light.app.SearchUseCase
//import com.example.light.di.repository.ResponseCallback
//import com.example.light.model.ChordInfo
//import com.example.light.model.FetchDataModel
//import com.example.light.utils.DefaultResourceManager
//import com.example.light.utils.ResourceManager
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.launch
////import org.apache.commons.lang.StringUtils
//
//class ChordViewModel constructor(
//    private val chordUseCase: ChordUseCase,
//    private val chordSearchUseCase: SearchUseCase,
//    private val resourceManager: DefaultResourceManager
//) : ViewModel(){
//    private val _chordList = MutableLiveData<ResponseCallback<FetchDataModel>>()
//    val chordResponse: LiveData<ResponseCallback<FetchDataModel>>
//        get() = _chordList
//    private val _selectedChord = MutableLiveData<ResponseCallback<ChordInfo>>()
//    val selectedChordResponse: LiveData<ResponseCallback<ChordInfo>>
//        get() = _selectedChord
//    private var pagination = 1
//    private var searchChord = ""
//    private var updatedItems = arrayListOf<ChordInfo>()
//    init {
//        fetchImagesFromRemoteServer(pagination)
//    }
//
//    fun fetchImagesFromRemoteServer(pagination : Int){
//        _chordList.value = ResponseCallback.loading(
//            data = FetchDataModel(
//                page = pagination,
//                chordInfo = null
//            )
//        )
//        viewModelScope.launch {
//            try {
//                chordUseCase.invoke(page = pagination).collect { dataset ->
//                    dataset.chordInfo.let {
//                        if (it.isNotEmpty()) {
//                            if (pagination ==1) {
//                                updatedItems = arrayListOf()
//                                updatedItems.addAll(it)
//
//                            } else {
//                                updatedItems.addAll(it)
//                            }
//                            _chordList.postValue(
//                                ResponseCallback.success(
//                                    data = FetchDataModel(
//                                        page = pagination,
//                                        chordInfo = updatedItems
//                                    ),
//                                    resourceManager.getString(R.string.items_received)
//                                )
//                            )
//                        } else
//                            _chordList.value = ResponseCallback.error(
//                                data = FetchDataModel(
//                                    page = pagination,
//                                    chordInfo = null
//                                ),
//                                if (pagination == 1)
//                                    resourceManager.getString(R.string.no_images_received)
//                                else
//                                    resourceManager.getString(R.string.more_images)
//                            )
//                    }
//
//                }
//            } catch (e: Exception) {
//                _chordList.value = ResponseCallback.error(null, e.message.toString())
//            }
//        }
//    }
//    fun setSelectedProduct(singleImages: ChordInfo) {
//        _selectedChord.value = ResponseCallback.loading(null)
//        viewModelScope.launch {
//            try {
//                _selectedChord.value = ResponseCallback.success(
//                    data = singleImages,
//                    resourceManager.getString(R.string.items_received)
//                )
//            } catch (e: Exception) {
//                _chordList.value = ResponseCallback.error(null, e.message.toString())
//            }
//        }
//    }
//
//    /*
//    * load next page
//    * */
//    fun loadNextPageChord() {
//        pagination++
//        if (searchChord == "") {
//            fetchImagesFromRemoteServer(pagination)
//        } else {
//            fetchSearchImagesFromRemoteServer(pagination, searchChord)
//        }
//
//    }
//
//    /*
//    * Retry connection if internet is not available
//    * */
//    fun retryConnection() {
//        if (searchChord == "") {
//            fetchImagesFromRemoteServer(pagination)
//        } else {
//            fetchSearchImagesFromRemoteServer(pagination, searchChord)
//        }
//    }
//
//    /*
//    * Search function for searching photos by name
//    * */
//    fun searchChordFromRemote(search: String) {
//        pagination = 1
//        searchChord = search
//        fetchSearchImagesFromRemoteServer(pagination, search)
//    }
//
//    /*
//    * Query to fetch images from server
//    * */
//    private fun fetchSearchImagesFromRemoteServer(pagination: Int, position: String) {
//        _chordList.value = ResponseCallback.loading(
//            data = FetchDataModel(
//                page = pagination,
//                chordInfo = null
//            )
//        )
//        viewModelScope.launch {
//            try {
//                chordSearchUseCase(page = pagination, position = position).collect { dataset ->
//                    dataset.chordInfo.let {
//                        if (it.isNotEmpty()) {
//                            if (pagination == 1) {
//                                updatedItems = arrayListOf()
//                                updatedItems.addAll(it)
//                            } else {
//                                updatedItems.addAll(it)
//                            }
//                            _chordList.postValue(
//                                ResponseCallback.success(
//                                    data = FetchDataModel(
//                                        page = pagination,
//                                        chordInfo = updatedItems
//                                    ),
//                                    resourceManager.getString(R.string.items_received)
//                                )
//                            )
//                        } else
//                            _chordList.value = ResponseCallback.error(
//                                data = FetchDataModel(
//                                    page = pagination,
//                                    chordInfo = null
//                                ),
//                                if (pagination == 1)
//                                    resourceManager.getString(R.string.no_images_received)
//                                else
//                                    resourceManager.getString(R.string.more_images)
//                            )
//                    }
//
//                }
//            } catch (e: Exception) {
//                _chordList.value = ResponseCallback.error(null, e.message.toString())
//            }
//        }
//    }
//}