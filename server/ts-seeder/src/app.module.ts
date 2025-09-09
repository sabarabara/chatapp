import { Module } from '@nestjs/common';
import { SeederModule } from './db/service/SeederModule';
import { ComponentsModule } from './db/service/components/ComponentsModule';
import { ConfigModule } from './db/config/ConfigModule';

@Module({
  imports: [SeederModule, ComponentsModule, ConfigModule],
  providers: [SeederModule, ComponentsModule, ConfigModule],
  exports: [SeederModule, ComponentsModule, ConfigModule],
})
export class AppModule {}
