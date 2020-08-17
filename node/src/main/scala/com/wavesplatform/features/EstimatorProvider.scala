package com.wavesplatform.features

import com.wavesplatform.features.BlockchainFeatures.{BlockReward, BlockV5}
import com.wavesplatform.lang.v1.estimator.v2.ScriptEstimatorV2
import com.wavesplatform.lang.v1.estimator.v3.ScriptEstimatorV3
import com.wavesplatform.lang.v1.estimator.{ScriptEstimator, ScriptEstimatorV1}
import com.wavesplatform.settings.WavesSettings
import com.wavesplatform.state.Blockchain

object EstimatorProvider {
  implicit class EstimatorBlockchainExt(b: Blockchain) {
    val estimator: ScriptEstimator =
      if (b.isFeatureActivated(BlockV5)) ScriptEstimatorV3.instance
      else if (b.isFeatureActivated(BlockReward)) ScriptEstimatorV2
      else ScriptEstimatorV1
  }

  implicit class EstimatorWavesSettingsExt(ws: WavesSettings) {
    val estimator: ScriptEstimator =
      if (ws.featuresSettings.supported.contains(BlockV5.id)) ScriptEstimatorV3.instance
      else if (ws.featuresSettings.supported.contains(BlockReward.id)) ScriptEstimatorV2
      else ScriptEstimatorV1
  }
}
