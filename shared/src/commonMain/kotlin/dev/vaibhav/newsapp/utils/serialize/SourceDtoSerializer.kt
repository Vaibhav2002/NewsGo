package dev.vaibhav.newsapp.utils.serialize

import dev.vaibhav.newsapp.data.models.remote.SourceDto

expect class SourceDtoSerializer():Serializer<SourceDto> {

    override fun serialize(data: SourceDto):String

    override fun deSerialize(data:String): SourceDto
}