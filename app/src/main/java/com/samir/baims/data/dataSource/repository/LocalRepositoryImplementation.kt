package com.samir.baims.data.dataSource.repository

import com.samir.baims.data.dataSource.base.PreferencesManager
import com.samir.baims.domain.repository.dataSource.LocalRepository

import javax.inject.Inject

class LocalRepositoryImplementation @Inject constructor(
    private val prefManager: PreferencesManager
): LocalRepository
{

}