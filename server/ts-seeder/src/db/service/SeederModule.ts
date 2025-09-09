import { Module } from '@nestjs/common';
import { SeedService } from './SeedService';
import { ComponentsModule } from './components/ComponentsModule';

@Module({
  imports: [ComponentsModule],
  providers: [SeedService, ComponentsModule],
  exports: [SeedService, ComponentsModule],
})
export class SeederModule {}
