package com.example.geminichatbot

import com.example.geminichatbot.data.TextualAiContentRepository
import com.example.geminichatbot.domain.AiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAiRepository(): AiRepository {
        return TextualAiContentRepository()
    }
}