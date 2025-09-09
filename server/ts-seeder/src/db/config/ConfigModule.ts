import { Module } from '@nestjs/common';
import { DbService } from './DbService';
import { RagDbService } from './RagDBService';


@Module({
    providers: [DbService, RagDbService],
    exports: [DbService, RagDbService],
})
export class ConfigModule {}
