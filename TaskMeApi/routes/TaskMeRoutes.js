const express = require('express');
const router = express.Router();

const {
    registerUser,
    postTask,
    postReview,
    postCategory,
    postNotification,
    getUser,
    getAllUsersByCatgory,
    getAllTaskByRole,
    getAllReviewsByUser,
    getTaskById,
    getAllNotificationsByUser,
    getAllCategories,
    updateUser,
    updateTaskState,
    updateNotificationState,
    deleteNotification,
    getAllUsers
} = require('../controllers/TaskMeControllers');

/*RUTAS POST*/

router.post('/users/register', registerUser);                           //FUNCIONA
router.post('/tasks', postTask);                                        //FUNCIONA
router.post('/reviews', postReview);                                    //FUNCIONA
router.post('/categories', postCategory);                               //FUNCIONA
router.post('/notifications', postNotification);                        //FUNCIONA

/*RUTAS GET y login de usuario*/

router.post('/users/login', getUser);                                   //FUNCIONA
router.get('/users/:categoryId', getAllUsersByCatgory);                 //FUNCIONA
router.get('/tasks/role', getAllTaskByRole);                            //FUNCIONA
router.get('/reviews/:usuarioId', getAllReviewsByUser);                 //FUNCIONA
router.get('/tasks/:taskId', getTaskById);                              //FUNCIONA
router.get('/notifications/:usuarioId', getAllNotificationsByUser);     //FUNCIONA
router.get('/categories', getAllCategories);                           //FUNCIONA

/*RUTAS PUT/PATCH*/

router.patch('/users/:usuarioId', updateUser);                                  //FUNCIONA
router.patch('/tasks/:taskId/state', updateTaskState);                          //FUNCIONA
router.patch('/notifications/:notificationId/state', updateNotificationState);  //Probando

/*RUTAS DELETE*/

router.delete('/notifications/:notificationId', deleteNotification);


router.get('/users', getAllUsers);

module.exports = router;