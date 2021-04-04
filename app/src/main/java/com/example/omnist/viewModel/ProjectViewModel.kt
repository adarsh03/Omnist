package com.example.omnist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.omnist.data.projectData.ProjectEntity
import com.example.omnist.repo.ProjectRepository
import com.example.omnist.utils.Coroutines

class ProjectViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: ProjectRepository

    init {
        repository = ProjectRepository(application)
    }

    fun getProjectFromAPIAndStore() {
        Coroutines.main {
            repository.projectDataApiCall()
        }
    }


    fun getAllProjectData(): LiveData<List<ProjectEntity>> {
        return repository.getProjectsFromDatabase()
    }

}