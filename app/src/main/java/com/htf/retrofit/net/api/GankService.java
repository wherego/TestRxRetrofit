/*
 * {EasyGank}  Copyright (C) {2015}  {CaMnter}
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <http://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 */

package com.htf.retrofit.net.api;

import com.htf.rxretrofit.bean.Beauties;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Description：GankService
 * Created by：CaMnter
 * Time：2016-01-03 16:18
 */
public interface GankService {

    String BASE_URL = "http://gank.io/api/";
    String GANK_DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    int DEFAULT_DATA_SIZE = 10;

    /**
     * @param size 数据个数
     * @param page 第几页
     * @return Observable<GankWelfare>
     */
    @GET("data/福利/{size}/{page}")
    Observable<Beauties> getData(@Path("size") int size, @Path("page") int page);
}

