package luna724.iloveichika.lunadayo.features

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.EntityRenderer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.opengl.GL11
import java.lang.reflect.Method


class PlayerESP {
    private val mc: Minecraft = Minecraft.getMinecraft()

    @SubscribeEvent
    fun onRenderWorldLast(event: RenderWorldLastEvent) {
        // 部分的なティック（補間値）
        val partialTicks = event.partialTicks

        // 3D描画のための設定
        GlStateManager.pushMatrix()
        // プレイヤー視点に合わせる（カメラの逆行列を適用）
        try {
            val method: Method = EntityRenderer::class.java.getDeclaredMethod(
                "setupCameraTransform",
                Float::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            method.setAccessible(true)
            // 第2引数のpassは0または1(視点パス)。状況によって変えるが、通常0でOK
            method.invoke(mc.entityRenderer, partialTicks, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 描画のためのGL設定（ラインの太さ・深度テストオフなど好みに応じて）
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glLineWidth(2.0f)

        // ワールドの全エンティティを走査
        for (entity in mc.theWorld.loadedEntityList) {
            // プレイヤーのみ対象
            if (entity is EntityPlayer) {
                if (entity === mc.thePlayer) {
                    // 自分自身を除外


                }
                drawPlayerESP(entity, partialTicks)
            }
        }

        // 後始末
        GL11.glEnable(GL11.GL_DEPTH_TEST)
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        GL11.glEnable(GL11.GL_LIGHTING)
        GlStateManager.popMatrix()
    }

    /**
     * プレイヤーの周囲にバウンディングボックスのラインを引く簡易ESP描画
     */
    private fun drawPlayerESP(player: EntityPlayer, partialTicks: Float) {
        // プレイヤーの位置を補間して取得
        val x = (player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks
                - mc.getRenderManager().viewerPosX)
        val y = (player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks
                - mc.getRenderManager().viewerPosY)
        val z = (player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks
                - mc.getRenderManager().viewerPosZ)

        // プレイヤーの当たり判定（幅・高さなど）
        val width = player.width / 2.0f // 左右半分に分けるため /2
        val height = player.height

        // バウンディングボックスの角を計算
        val minX = x - width
        val maxX = x + width
        val minY = y
        val maxY = y + height
        val minZ = z - width
        val maxZ = z + width

        // OpenGLのライン描画
        GL11.glBegin(GL11.GL_LINES)

        // 好きな色 (RGBA) に変更可
        GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f) // 赤色

        // 上面四辺
        GL11.glVertex3d(minX, maxY, minZ)
        GL11.glVertex3d(maxX, maxY, minZ)

        GL11.glVertex3d(maxX, maxY, minZ)
        GL11.glVertex3d(maxX, maxY, maxZ)

        GL11.glVertex3d(maxX, maxY, maxZ)
        GL11.glVertex3d(minX, maxY, maxZ)

        GL11.glVertex3d(minX, maxY, maxZ)
        GL11.glVertex3d(minX, maxY, minZ)

        // 下面四辺
        GL11.glVertex3d(minX, minY, minZ)
        GL11.glVertex3d(maxX, minY, minZ)

        GL11.glVertex3d(maxX, minY, minZ)
        GL11.glVertex3d(maxX, minY, maxZ)

        GL11.glVertex3d(maxX, minY, maxZ)
        GL11.glVertex3d(minX, minY, maxZ)

        GL11.glVertex3d(minX, minY, maxZ)
        GL11.glVertex3d(minX, minY, minZ)

        // 縦ライン4本
        GL11.glVertex3d(minX, minY, minZ)
        GL11.glVertex3d(minX, maxY, minZ)

        GL11.glVertex3d(maxX, minY, minZ)
        GL11.glVertex3d(maxX, maxY, minZ)

        GL11.glVertex3d(maxX, minY, maxZ)
        GL11.glVertex3d(maxX, maxY, maxZ)

        GL11.glVertex3d(minX, minY, maxZ)
        GL11.glVertex3d(minX, maxY, maxZ)

        GL11.glEnd()
    }
}